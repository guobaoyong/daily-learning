package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 自媒体文章是否可用，默认应为1
 * <ul>
 *     <li>0: 不可用</li>
 *     <li>1: 可用</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 12:03
 */
@Getter
public enum NewsEnable {
    DISABLE((short) 0, "不可用"),
    ENABLE((short) 1, "可用");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String name;

    NewsEnable(Short value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 根据指定值获取枚举对象
     */
    public static NewsEnable of(short value) {
        for (NewsEnable newsEnable : NewsEnable.values()) {
            if (newsEnable.value == value) {
                return newsEnable;
            }
        }
        return null;
    }

    /**
     * 判断指定值是否匹配任意枚举值
     */
    public static boolean isValid(short value) {
        return DISABLE.value.equals(value) || ENABLE.value.equals(value);
    }
}
