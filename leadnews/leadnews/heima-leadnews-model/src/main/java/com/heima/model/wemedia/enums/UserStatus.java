package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 自媒体用户状态
 * <ul>
 *     <li>0: 暂时不可用</li>
 *     <li>1: 永久不可用</li>
 *     <li>9: 正常可用</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 11:46
 */
@Getter
public enum UserStatus {
    TEMPORARY_DISABLE(0, "暂时不可用"),
    PERMANENT_DISABLE(1, "永久不可用"),
    NORMAL(9, "正常可用");

    @EnumValue
    @JsonValue
    final Integer value;
    final String desc;

    UserStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
