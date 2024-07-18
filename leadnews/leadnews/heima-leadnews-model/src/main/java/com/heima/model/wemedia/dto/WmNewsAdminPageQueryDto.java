package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 查询需要人工审核的文章 DTO
 *
 * @author 高翔宇
 * @since 2024/4/16 周二 上午9:34
 */
@Data
public class WmNewsAdminPageQueryDto {
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页大小
     */
    private Integer size;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章状态
     */
    private Short status;
}
