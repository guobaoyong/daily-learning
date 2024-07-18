package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 管理员驳回文章 DTO
 *
 * @author 高翔宇
 * @since 2024/4/16 周二 下午10:33
 */
@Data
public class AdminRejectNewsDto {
    /**
     * 文章id
     */
    private Long id;
    /**
     * 驳回原因
     */
    private String msg;
}
