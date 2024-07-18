package com.heima.model.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * APP实名认证状态
 * <li>0 创建中</li>
 * <li>1 待审核</li>
 * <li>2 审核失败</li>
 * <li>9 审核通过</li>
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:17
 */
@Getter
public enum ApUserRealNameStatus {
    CREATE((short) 0, "创建中"),
    PENDING((short) 1, "待审核"),
    FAIL((short) 2, "审核失败"),
    PASS((short) 9, "审核通过");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String desc;

    ApUserRealNameStatus(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
