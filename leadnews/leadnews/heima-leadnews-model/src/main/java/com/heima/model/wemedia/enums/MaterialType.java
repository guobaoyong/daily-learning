package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 自媒体文章的素材的类型
 * <ul>
 *     <li>0: 图片</li>
 *     <li>1: 视频</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 11:57
 */
@Getter
public enum MaterialType {
    IMAGE((short) 0, "图片"),
    VIDEO((short) 1, "视频");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String name;

    MaterialType(Short value, String name) {
        this.value = value;
        this.name = name;
    }
}
