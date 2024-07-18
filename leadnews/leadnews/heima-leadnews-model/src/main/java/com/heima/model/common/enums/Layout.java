package com.heima.model.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 文章的布局：
 * <ul>
 *     <li>0 无图文章</li>
 *     <li>1 单图文章</li>
 *     <li>2 多图文章</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 10:21
 */
@Getter
public enum Layout {
    NO_IMG((short) 0, "无图文章"),
    SINGLE_IMG((short) 1, "单图文章"),
    MULTIPLE_IMG((short) 2, "多图文章");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String desc;

    Layout(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Layout valueOf(Short value) {
        for (Layout layout : Layout.values()) {
            if (layout.getValue().equals(value)) {
                return layout;
            }
        }
        return null;
    }
}
