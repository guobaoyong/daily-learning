package com.heima.model.wemedia.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 敏感词信息表
 *
 * @author 高翔宇
 * @since 2024/3/16 周六 上午9:34
 */
@Data
@Builder
@Schema(description = "敏感词信息表")
@AllArgsConstructor
@NoArgsConstructor
public class WmSensitive {
    private Long id;
    /**
     * 敏感词
     */
    private String sensitives;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}
