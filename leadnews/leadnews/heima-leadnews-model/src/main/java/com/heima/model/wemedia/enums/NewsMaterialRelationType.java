package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 文章和素材的关系的类型
 * <ul>
 *     <li>0: 内容引用</li>
 *     <li>1: 封面</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 12:09
 */
@Getter
public enum NewsMaterialRelationType {
    CONTENT_REFERENCE(0, "内容引用"),
    COVER(1, "封面");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    NewsMaterialRelationType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
