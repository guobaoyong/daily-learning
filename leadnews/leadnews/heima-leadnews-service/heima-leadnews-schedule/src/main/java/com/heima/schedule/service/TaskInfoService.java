package com.heima.schedule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.schedule.entity.TaskInfo;
import com.heima.model.schedule.dto.TaskDto;

/**
 * @author 高翔宇
 * @since 2024/3/30 周六 上午9:39
 */
public interface TaskInfoService extends IService<TaskInfo> {

    /**
     * 添加任务
     *
     * @param taskDto 任务信息
     * @return 任务ID
     */
    Long addTask(TaskDto taskDto);

    /**
     * 取消任务
     *
     * @param taskId 任务ID
     * @return 是否取消成功
     */
    boolean cancelTask(Long taskId);

    /**
     * 从任务队列中拉取任务
     *
     * @param type     任务类型
     * @param priority 优先级
     * @return 任务信息
     */
    TaskDto pullTask(int type, int priority);

    /**
     * 刷新任务。将Redis ZSet中时间戳不大于当前时间的任务放入任务队列
     */
    void refreshTask();

    /**
     * 从数据库中加载任务，同步至缓存
     */
    void reloadTask();
}
