package com.heima.api.schedule.fallback;

import com.heima.api.schedule.ScheduleClient;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.schedule.dto.TaskDto;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author 高翔宇
 * @since 2024/4/11 周四 下午1:30
 */
public class ScheduleFallbackFactory implements FallbackFactory<ScheduleClient> {
    @Override
    public ScheduleClient create(Throwable cause) {
        return new ScheduleClient() {
            /**
             * 添加任务
             *
             * @param taskDto 任务信息
             * @return 任务ID
             */
            @Override
            public ResponseResult<Long> addTask(TaskDto taskDto) {
                return ResponseResult.errorResult(503, cause.getMessage());
            }

            /**
             * 取消任务
             *
             * @param taskId 任务ID
             * @return 是否取消成功
             */
            @Override
            public ResponseResult<Boolean> cancelTask(Long taskId) {
                return ResponseResult.errorResult(503, cause.getMessage());
            }

            /**
             * 从任务队列中拉取任务
             *
             * @param type     任务类型
             * @param priority 优先级
             * @return 任务信息
             */
            @Override
            public ResponseResult<TaskDto> pullTask(int type, int priority) {
                return ResponseResult.errorResult(503, cause.getMessage());
            }
        };
    }
}
