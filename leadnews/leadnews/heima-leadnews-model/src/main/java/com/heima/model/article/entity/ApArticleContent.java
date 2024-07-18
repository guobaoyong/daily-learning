package com.heima.model.article.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午3:28
 */
@Data
@Schema(description = "文章内容表")
@Builder
public class ApArticleContent {
    /**
     * 主键
     */
    @Schema(description = "文章内容id")
    private Long id;

    /**
     * 文章ID
     */
    @Schema(description = "文章ID")
    private Long articleId;

    /**
     * 文章内容
     */
    @Schema(description = "文章内容")
    private String content;
}
