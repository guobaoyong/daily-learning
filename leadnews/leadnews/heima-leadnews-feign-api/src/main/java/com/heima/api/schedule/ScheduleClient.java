package com.heima.api.schedule;

import com.heima.api.schedule.fallback.ScheduleFallbackFactory;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.schedule.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 高翔宇
 * @since 2024/3/31 周日 下午3:57
 */
@FeignClient(value = "leadnews-schedule", path = "/api/v1/task", fallbackFactory = ScheduleFallbackFactory.class)
public interface ScheduleClient {
    /**
     * 添加任务
     *
     * @param taskDto 任务信息
     * @return 任务ID
     */
    @PostMapping("/add")
    ResponseResult<Long> addTask(@RequestBody TaskDto taskDto);

    /**
     * 取消任务
     *
     * @param taskId 任务ID
     * @return 是否取消成功
     */
    @GetMapping("/{taskId}")
    ResponseResult<Boolean> cancelTask(@PathVariable("taskId") Long taskId);

    /**
     * 从任务队列中拉取任务
     *
     * @param type     任务类型
     * @param priority 优先级
     * @return 任务信息
     */
    @GetMapping("/pull/{type}/{priority}")
    ResponseResult<TaskDto> pullTask(@PathVariable("type") int type, @PathVariable("priority") int priority);
}
