package com.heima.model.user.dto;

import com.heima.model.user.enums.ApUserRealnameStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * 实名认证分页查询 DTO
 *
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:42
 */
@Data
public class ApUserRealnamePageQueryDto implements Serializable {
    private Integer page;
    private Integer size;
    /**
     * 实名认证状态。
     * 请使用{@link ApUserRealnameStatus#fromValue(Short)}转换为枚举
     */
    private Short status;
}
