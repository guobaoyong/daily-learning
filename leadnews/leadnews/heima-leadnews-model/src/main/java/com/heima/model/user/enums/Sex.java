package com.heima.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 0 男
 * 1 女
 * 2 未知
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 10:54
 */
@Getter
public enum Sex {
    MALE(0, "男"),
    FEMALE(1, "女"),
    UNKNOWN(2, "未知");

    @EnumValue
    @JsonValue
    final Integer value;
    final String desc;

    Sex(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
