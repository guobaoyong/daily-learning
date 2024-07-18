package com.heima.schedule.controller;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.schedule.dto.TaskDto;
import com.heima.schedule.service.TaskInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author 高翔宇
 * @since 2024/3/31 周日 下午4:05
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/task")
public class ScheduleController {
    private final TaskInfoService taskInfoService;

    /**
     * 添加任务
     *
     * @param taskDto 任务信息
     * @return 任务ID
     */
    @PostMapping("/add")
    public ResponseResult<Long> addTask(@RequestBody TaskDto taskDto) {
        log.info("添加任务：{}", taskDto);
        return ResponseResult.okResult(taskInfoService.addTask(taskDto));
    }

    /**
     * 取消任务
     *
     * @param taskId 任务ID
     * @return 是否取消成功
     */
    @GetMapping("/{taskId}")
    public ResponseResult<Boolean> cancelTask(@PathVariable("taskId") Long taskId) {
        log.info("取消任务：{}", taskId);
        return ResponseResult.okResult(taskInfoService.cancelTask(taskId));
    }

    /**
     * 从任务队列（redis-list）中拉取任务
     *
     * @param type     任务类型
     * @param priority 优先级
     * @return 任务信息
     */
    @GetMapping("/pull/{type}/{priority}")
    public ResponseResult<TaskDto> pullTask(@PathVariable("type") int type, @PathVariable("priority") int priority) {
        log.info("从任务队列（redis-list）中拉取任务：type={}, priority={}", type, priority);
        return ResponseResult.okResult(taskInfoService.pullTask(type, priority));
    }
}
