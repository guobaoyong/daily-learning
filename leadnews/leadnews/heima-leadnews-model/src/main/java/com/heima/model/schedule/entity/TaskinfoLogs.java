package com.heima.model.schedule.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.heima.model.schedule.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author itheima
 */
@Data
@TableName("taskinfo_logs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskinfoLogs implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(type = IdType.NONE)
    private Long taskId;

    /**
     * 执行时间
     */
    @TableField("execute_time")
    private Date executeTime;

    /**
     * 参数
     */
    @TableField("parameters")
    private byte[] parameters;

    /**
     * 优先级
     */
    @TableField("priority")
    private Integer priority;

    /**
     * 任务类型
     */
    @TableField("task_type")
    private Integer taskType;

    /**
     * 版本号，用乐观锁
     */
    @Version
    private Integer version;

    /**
     * 状态 0=init 1=EXECUTED 2=CANCELLED
     */
    @TableField("status")
    private TaskStatus status;
}
