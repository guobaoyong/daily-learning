package com.heima.model.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * admin状态
 * <ul>
 *     <li>0 暂时不可用</li>
 *     <li>1 永久不可用</li>
 *     <li>9 正常可用</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:12
 */
@Getter
public enum AdminStatus {
    DISABLE(0, "暂时不可用"),
    PERMANENT_DISABLE(1, "永久不可用"),
    ENABLE(9, "正常可用");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    AdminStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
