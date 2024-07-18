package com.heima.model.article.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 文章的标记
 * <ul>
 *     <li>0 普通文章</li>
 *     <li>1 热点文章</li>
 *     <li>2 置顶文章</li>
 *     <li>3 精品文章</li>
 *     <li>4 大V文章</li>
 * </ul>
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 10:33
 */
@Getter
public enum Flag {
    NORMAL(0, "普通文章"),
    HOT(1, "热点文章"),
    TOP(2, "置顶文章"),
    BOUTIQUE(3, "精品文章"),
    BIG_V(4, "大V文章");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    Flag(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
