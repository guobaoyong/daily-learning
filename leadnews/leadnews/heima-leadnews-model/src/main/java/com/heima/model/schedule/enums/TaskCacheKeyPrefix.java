package com.heima.model.schedule.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存key前缀
 *
 * @author 高翔宇
 * @since 2024/3/30 周六 上午10:18
 */
@Getter
@AllArgsConstructor
public enum TaskCacheKeyPrefix {
    /**
     * 未来数据key前缀
     */
    FUTURE("future_", "未来数据key前缀"),

    /**
     * 当前数据key前缀
     */
    CURRENT("current_", "当前数据key前缀");

    @EnumValue
    @JsonValue
    private final String value;
    private final String desc;

    @Override
    public String toString() {
        return this.value;
    }
}
