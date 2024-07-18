package com.heima.model.user.dto;

import lombok.Data;

/**
 * 实名认证驳回 DTO
 *
 * @author 高翔宇
 * @since 2024/4/16 周二 上午8:57
 */
@Data
public class ApUserRealnameRejectDto {
    /**
     * 实名认证id
     */
    private Long id;
    /**
     * 驳回原因
     */
    private String msg;
}
