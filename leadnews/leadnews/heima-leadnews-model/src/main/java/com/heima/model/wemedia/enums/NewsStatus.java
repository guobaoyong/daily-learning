package com.heima.model.wemedia.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 当前状态
 * <ul>
 *     <li>0: 草稿</li>
 *     <li>1: 提交（待审核）</li>
 *     <li>2: 审核失败</li>
 *     <li>3: 人工审核</li>
 *     <li>4: 人工审核通过</li>
 *     <li>8: 审核通过（待发布）</li>
 *     <li>9: 已发布</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 12:00
 */
@Getter
public enum NewsStatus {
    DRAFT((short) 0, "草稿"),
    SUBMIT((short) 1, "提交（待审核）"),
    FAILED((short) 2, "审核失败"),
    MANUAL_REVIEW((short) 3, "人工审核"),
    MANUAL_REVIEW_PASSED((short) 4, "人工审核通过"),
    PASSED((short) 8, "审核通过（待发布）"),
    PUBLISHED((short) 9, "已发布");

    @EnumValue
    @JsonValue
    private final Short value;
    private final String name;

    NewsStatus(Short value, String name) {
        this.value = value;
        this.name = name;
    }

    public static NewsStatus valueOf(Short value) {
        for (NewsStatus newsStatus : NewsStatus.values()) {
            if (newsStatus.getValue().equals(value)) {
                return newsStatus;
            }
        }
        return null;
    }
}
