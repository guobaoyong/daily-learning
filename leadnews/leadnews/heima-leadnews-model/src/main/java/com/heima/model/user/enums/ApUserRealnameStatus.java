package com.heima.model.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 实名认证状态
 * <ul>
 *     <li>0 - 创建中</li>
 *     <li>1 - 待审核</li>
 *     <li>2 - 审核失败</li>
 *     <li>9 - 审核通过</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:27
 */
@Getter
public enum ApUserRealnameStatus {
    CREATING((short) 0, "创建中"),
    SUBMITTED((short) 1, "待审核"),
    REJECTED((short) 2, "审核失败"),
    APPROVED((short) 9, "审核通过");

    @EnumValue
    @JsonValue
    final Short value;
    final String desc;

    ApUserRealnameStatus(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取枚举
     */
    public static ApUserRealnameStatus fromValue(Short value) {
        for (ApUserRealnameStatus status : ApUserRealnameStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }
}
