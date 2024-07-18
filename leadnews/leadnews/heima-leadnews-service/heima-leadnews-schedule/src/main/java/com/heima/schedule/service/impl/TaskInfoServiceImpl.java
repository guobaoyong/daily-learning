package com.heima.schedule.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.redis.CacheService;
import com.heima.model.schedule.entity.TaskInfo;
import com.heima.model.schedule.entity.TaskinfoLogs;
import com.heima.model.schedule.dto.TaskDto;
import com.heima.model.schedule.enums.TaskCacheKeyPrefix;
import com.heima.model.schedule.enums.TaskStatus;
import com.heima.schedule.mapper.TaskInfoLogsMapper;
import com.heima.schedule.mapper.TaskInfoMapper;
import com.heima.schedule.service.TaskInfoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author 高翔宇
 * @since 2024/3/30 周六 上午9:40
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {
    private final CacheService cacheService;
    private final TaskInfoMapper taskInfoMapper;
    private final TaskInfoLogsMapper taskinfoLogsMapper;

    /**
     * 添加任务
     *
     * @param taskDto 任务信息
     * @return 任务ID
     */
    @Override
    public Long addTask(TaskDto taskDto) {
        if (addTaskToDb(taskDto)) {
            addTaskToCache(taskDto);
        } else {
            log.error("添加任务到数据库失败：{}", taskDto);
        }
        return taskDto.getTaskId();
    }

    /**
     * 将任务添加到数据库中
     *
     * @param taskDto 任务信息
     * @return 是否添加成功
     */
    private boolean addTaskToDb(TaskDto taskDto) {
        try {
            // 保存任务信息
            taskDto.setTaskId(null);
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setParameters(taskDto.getParameters());
            taskInfo.setPriority(taskDto.getPriority());
            taskInfo.setTaskType(taskDto.getTaskType());
            taskInfo.setExecuteTime(new Date(taskDto.getExecuteTime()));
            taskInfoMapper.insert(taskInfo);
            taskDto.setTaskId(taskInfo.getTaskId());

            // 保存任务日志信息
            TaskinfoLogs taskinfoLogs = new TaskinfoLogs();
            taskinfoLogs.setTaskId(taskInfo.getTaskId());
            taskinfoLogs.setTaskType(taskInfo.getTaskType());
            taskinfoLogs.setParameters(taskInfo.getParameters());
            taskinfoLogs.setPriority(taskInfo.getPriority());
            taskinfoLogs.setExecuteTime(taskInfo.getExecuteTime());
            taskinfoLogs.setStatus(TaskStatus.INIT);
            taskinfoLogs.setVersion(1);
            taskinfoLogsMapper.insert(taskinfoLogs);
            return true;
        } catch (Exception e) {
            log.error("添加任务到数据库失败：{}", taskDto, e);
        }
        return false;
    }

    /**
     * 将任务添加到缓存中
     *
     * @param taskDto 任务信息
     */
    private void addTaskToCache(TaskDto taskDto) {
        String key = taskDto.getTaskType() + "_" + taskDto.getPriority();
        Long executeTime = taskDto.getExecuteTime();
        Calendar now = Calendar.getInstance();
        // 当前时间的时间戳
        long currentTimeInMillis = now.getTimeInMillis();
        now.add(Calendar.MINUTE, 5);
        // 5分钟之后的时间的时间戳
        long after5MinutesTimeInMillis = now.getTimeInMillis();
        if (executeTime <= currentTimeInMillis) {
            log.info("任务执行时间在当前时间之前，放入Redis-List中：{}", taskDto);
            // 如果任务执行时间在当前时间之前，放入Redis队列中
            cacheService.lLeftPush(TaskCacheKeyPrefix.CURRENT.getValue() + key, JSON.toJSONString(taskDto));
        } else if (executeTime <= after5MinutesTimeInMillis) {
            log.info("任务执行时间在当前时间之后，且与当前时间相差不超过5分钟，放入Redis-ZSet中：{}", taskDto);
            // 如果任务执行时间在当前时间之后，且与当前时间相差不超过指定时间，则放入Redis集合中，score为执行时间戳
            cacheService.zAdd(TaskCacheKeyPrefix.FUTURE.getValue() + key, JSON.toJSONString(taskDto), executeTime);
        }
    }

    /**
     * 取消任务
     *
     * @param taskId 任务ID
     * @return 是否取消成功
     */
    @Override
    public boolean cancelTask(Long taskId) {
        try {
            TaskInfo taskInfo = taskInfoMapper.selectById(taskId);
            if (taskInfo == null) {
                log.error("任务不存在：{}", taskId);
                return false;
            }
            taskInfoMapper.deleteById(taskId);

            // 更新任务状态
            TaskinfoLogs taskinfoLogs = taskinfoLogsMapper.selectById(taskId);
            if (taskinfoLogs != null) {
                taskinfoLogs.setStatus(TaskStatus.CANCELLED);
                taskinfoLogsMapper.updateById(taskinfoLogs);
            }

            // 删除缓存
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(taskInfo, taskDto);
            taskDto.setExecuteTime(taskInfo.getExecuteTime().getTime());
            Integer cacheKey = taskInfo.getTaskType();
            String jsonString = JSON.toJSONString(taskDto);
            if (taskDto.getExecuteTime() <= System.currentTimeMillis()) {
                cacheService.lRemove(TaskCacheKeyPrefix.CURRENT.getValue() + cacheKey + "_" + taskDto.getPriority(), 0, jsonString);
            } else {
                cacheService.zRemove(TaskCacheKeyPrefix.FUTURE.getValue() + cacheKey + "_" + taskDto.getPriority(), jsonString);
            }
        } catch (BeansException e) {
            log.error("取消任务失败：{}", taskId, e);
            return false;
        }
        return true;
    }

    /**
     * 从任务队列中拉取任务
     *
     * @param type     任务类型
     * @param priority 优先级
     * @return 任务信息
     */
    @Override
    public TaskDto pullTask(int type, int priority) {
        String key = type + "_" + priority;
        try {
            // 从当前任务队列中取出任务
            String taskJson = cacheService.lRightPop(TaskCacheKeyPrefix.CURRENT.getValue() + key);
            if (taskJson != null) {
                TaskDto taskDto = JSON.parseObject(taskJson, TaskDto.class);
                taskinfoLogsMapper.updateById(TaskinfoLogs.builder().taskId(taskDto.getTaskId()).status(TaskStatus.EXECUTED).build());
                return taskDto;
            }
        } catch (Exception e) {
            log.error("从当前任务队列中取出任务失败：{}", key, e);
        }
        return null;
    }

    /**
     * 刷新任务。将Redis-ZSet中 时间戳不大于当前时间的未完成的任务 放入 任务队列（Redis-List）。每分钟执行一次
     */
    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshTask() {
        String token = cacheService.tryLock("FUTURE_TASK_SYNC", 1000 * 30);
        if (StringUtils.hasLength(token)) {
            log.info("定时任务-刷新任务队列");
            Set<String> futureKeys = cacheService.scan(TaskCacheKeyPrefix.FUTURE.getValue() + "*");
            for (String futureKey : futureKeys) {
                String newCurrentKey = TaskCacheKeyPrefix.CURRENT.getValue() + futureKey.split(TaskCacheKeyPrefix.FUTURE.getValue())[1];
                Set<String> taskJsons = cacheService.zRangeByScore(futureKey, 0, System.currentTimeMillis());
                if (taskJsons != null && !taskJsons.isEmpty()) {
                    cacheService.refreshWithPipeline(futureKey, newCurrentKey, taskJsons);
                    log.info("刷新任务队列：{} -> {}", futureKey, newCurrentKey);
                }
            }
        }
    }

    /**
     * 从数据库中加载任务，同步至缓存，每5分钟执行一次。<br />
     * {@link PostConstruct} 注解表示在
     * Spring
     * 容器初始化时执行该方法
     */
    @Override
    @Scheduled(cron = "0 0/5 * * * ?")
    @PostConstruct
    public void reloadTask() {
        log.info("定时任务-从数据库中加载任务");
        clearCache();
        List<TaskInfo> taskInfos = taskInfoMapper.selectList(new LambdaQueryWrapper<TaskInfo>()
                .lt(TaskInfo::getExecuteTime, new Date().toInstant().plus(5, ChronoUnit.MINUTES)));
        HashMap<Long, TaskinfoLogs> taskLogsHashMap = new HashMap<>();
        taskinfoLogsMapper.selectBatchIds(taskInfos.stream().map(TaskInfo::getTaskId).toList())
                .forEach(taskinfoLogs -> taskLogsHashMap.put(taskinfoLogs.getTaskId(), taskinfoLogs));
        taskInfos.stream()
                .filter(taskInfo -> taskLogsHashMap.get(taskInfo.getTaskId()).getStatus().equals(TaskStatus.INIT))
                .forEach(taskInfo -> {
                    TaskDto taskDto = new TaskDto();
                    BeanUtils.copyProperties(taskInfo, taskDto);
                    taskDto.setExecuteTime(taskInfo.getExecuteTime().getTime());
                    addTaskToCache(taskDto);
                });
    }

    /**
     * 清空缓存
     */
    private void clearCache() {
        Set<String> currentKeys = cacheService.scan(TaskCacheKeyPrefix.CURRENT.getValue() + "*");
        Set<String> futureKey = cacheService.scan(TaskCacheKeyPrefix.FUTURE.getValue() + "*");
        cacheService.delete(currentKeys);
        cacheService.delete(futureKey);
    }
}
