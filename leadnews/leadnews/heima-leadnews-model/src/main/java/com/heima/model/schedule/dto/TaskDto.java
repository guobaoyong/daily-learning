package com.heima.model.schedule.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 高翔宇
 * @since 2024-03-30, 周六, 10:33
 */
@Data
public class TaskDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 执行时间，时间戳
     */
    private Long executeTime;

    /**
     * 参数
     */
    private byte[] parameters;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 任务类型
     */
    private Integer taskType;
}

