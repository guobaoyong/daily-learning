package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 自媒体账号类型
 * <ul>
 *     <li>0: 个人</li>
 *     <li>1: 企业</li>
 *     <li>2: 子账号</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 11:47
 */
@Getter
public enum UserType {
    PERSONAL(0, "个人"),
    ENTERPRISE(1, "企业"),
    SUB_ACCOUNT(2, "子账号");

    @EnumValue
    @JsonValue
    final Integer value;
    final String desc;

    UserType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
