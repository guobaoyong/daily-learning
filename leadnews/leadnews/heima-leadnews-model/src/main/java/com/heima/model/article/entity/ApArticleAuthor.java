package com.heima.model.article.entity;

import com.heima.model.article.enums.AuthorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午3:28
 */
@Data
@Schema(description = "文章作者信息数据模型")
public class ApArticleAuthor {
    /**
     * 主键
     */
    @Schema(description = "文章作者id")
    private Long id;

    /**
     * 文章作者的昵称
     */
    @Schema(description = "文章作者的昵称")
    private String name;

    /**
     * 类型
     * <ul>
     *     <li>0 爬取数据</li>
     *     <li>1 签约合作商</li>
     *     <li>2 平台自媒体人</li>
     * </ul>
     */
    @Schema(description = "类型")
    private AuthorType type;

    /**
     * 社交账号ID
     */
    @Schema(description = "社交账号ID")
    private Long userId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 自媒体账号ID
     */
    @Schema(description = "自媒体账号ID")
    private Integer wmUserId;
}
