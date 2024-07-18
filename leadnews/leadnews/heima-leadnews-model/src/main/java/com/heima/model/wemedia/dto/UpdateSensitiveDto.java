package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午4:24
 */
@Data
public class UpdateSensitiveDto {
    private Long id;
    /**
     * 敏感词
     */
    private String sensitives;
}
