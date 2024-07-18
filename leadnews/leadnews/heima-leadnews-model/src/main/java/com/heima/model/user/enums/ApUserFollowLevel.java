package com.heima.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 关注度
 * <ul>
 *     <li>0: 偶尔感兴趣</li>
 *     <li>1: 一般</li>
 *     <li>2: 经常</li>
 *     <li>3: 高度</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/4/18 周四 上午9:23
 */
@Getter
public enum ApUserFollowLevel {
    OCCASIONALLY((short) 0, "偶尔感兴趣"),
    GENERAL((short) 1, "一般"),
    OFTEN((short) 2, "经常"),
    HIGH((short) 3, "高度");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String desc;

    ApUserFollowLevel(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
