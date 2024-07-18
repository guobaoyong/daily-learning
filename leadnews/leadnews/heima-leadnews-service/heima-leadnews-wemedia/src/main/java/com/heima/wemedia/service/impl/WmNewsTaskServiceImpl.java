package com.heima.wemedia.service.impl;

import com.heima.api.schedule.ScheduleClient;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.schedule.dto.TaskDto;
import com.heima.model.schedule.enums.TaskTypeEnum;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.utils.common.ProtostuffUtil;
import com.heima.wemedia.service.WmAutoScanService;
import com.heima.wemedia.service.WmNewsTaskService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/3/31 周日 下午5:08
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WmNewsTaskServiceImpl implements WmNewsTaskService {
    private final ScheduleClient scheduleClient;
    private final WmAutoScanService wmAutoScanService;

    /**
     * 添加图文消息到任务队列
     *
     * @param id          图文消息ID
     * @param publishTime 发布时间
     */
    @Override
    @Async
    @GlobalTransactional
    @Transactional
    public void addNewsToTaskQueue(@NonNull Long id, Date publishTime) {
        log.info("添加图文消息到任务队列。id：{}，publishTime：{}", id, publishTime);
        TaskDto taskDto = new TaskDto();
        taskDto.setExecuteTime((publishTime == null ? new Date() : publishTime).getTime());
        taskDto.setTaskType(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType());
        taskDto.setPriority(TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        taskDto.setParameters(ProtostuffUtil.serialize(WmNews.builder().id(id).build()));
        ResponseResult<Long> res = scheduleClient.addTask(taskDto);
        if (res == null || res.getCode() != 200) {
            log.error("添加图文消息到任务队列失败。res：{}", res);
            throw new RuntimeException("添加图文消息到任务队列失败。res：" + res);
        }
    }

    /**
     * 从任务队列中拉取任务，审核并发布文章，每1秒执行一次
     */
    @Scheduled(cron = "0/1 * * * * ?")
    @Override
    public void scanNewsFromTaskQueue() {
        ResponseResult<TaskDto> res = scheduleClient.pullTask(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType(), TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        TaskDto taskDto = res.getData();
        if (res.getCode() == 200) {
            if (taskDto != null) {
                log.info("从任务队列中拉取任务，审核并发布文章...res：{}", res);
                wmAutoScanService.reviewNesById(ProtostuffUtil.deserialize(taskDto.getParameters(), WmNews.class).getId());
            }
        } else {
            log.error("从任务队列中拉取任务失败。res：{}", res);
        }
    }
}
