package com.heima.model.article.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午3:19
 */
@Data
@Builder
public class ApArticleConfig {
    /**
     * 主键
     */
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 是否可评论
     */
    private Boolean isComment;

    /**
     * 是否可转发
     */
    private Boolean isForward;

    /**
     * 是否已下架
     */
    private Boolean isDown;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

}
