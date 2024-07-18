package com.heima.model.article.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 类型
 * <ul>
 *     <li>0 爬取数据</li>
 *     <li>1 签约合作商</li>
 *     <li>2 平台自媒体人</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 10:37
 */
@Getter
public enum AuthorType {
    CRAWLER(0, "爬取数据"),
    COOPERATOR(1, "签约合作商"),
    PLATFORM(2, "平台自媒体人");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    AuthorType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
