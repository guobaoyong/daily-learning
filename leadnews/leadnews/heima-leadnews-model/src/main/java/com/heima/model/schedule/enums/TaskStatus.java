package com.heima.model.schedule.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态 0=init 1=EXECUTED 2=CANCELLED
 *
 * @author 高翔宇
 * @since 2024/3/30 周六 上午10:13
 */
@Getter
@AllArgsConstructor
public enum TaskStatus {
    /**
     * 0：初始化
     */
    INIT(0, "初始化"),

    /**
     * 1：已执行
     */
    EXECUTED(1, "已执行"),

    /**
     * 2：已取消
     */
    CANCELLED(2, "已取消");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    @Override
    public String toString() {
        return this.value.toString();
    }
}
