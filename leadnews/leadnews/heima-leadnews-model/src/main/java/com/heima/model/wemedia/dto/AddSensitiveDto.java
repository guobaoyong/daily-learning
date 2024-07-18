package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 添加或更新敏感词参数模型
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午3:50
 */
@Data
public class AddSensitiveDto {
    /**
     * 敏感词
     */
    private String sensitives;
}
