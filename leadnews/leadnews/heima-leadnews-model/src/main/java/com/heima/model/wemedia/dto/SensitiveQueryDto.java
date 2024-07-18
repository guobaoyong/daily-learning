package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午3:46
 */
@Data
public class SensitiveQueryDto {
    /**
     * 需要查询的敏感词
     */
    private String name;
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示条数
     */
    private Integer size;
}
