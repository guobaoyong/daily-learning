package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 素材是否收藏
 * <ul>
 *     <li>0: 否</li>
 *     <li>1: 是</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 11:54
 */
@Getter
public enum MaterialIsCollection {
    NO((short) 0, "否"),
    YES((short) 1, "是");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String name;

    MaterialIsCollection(Short value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MaterialIsCollection fromValue(Short value) {
        for (MaterialIsCollection materialIsCollection : MaterialIsCollection.values()) {
            if (materialIsCollection.value.equals(value)) {
                return materialIsCollection;
            }
        }
        return null;
    }
}
