package com.heima.model.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.article.enums.Flag;
import com.heima.model.common.enums.Layout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午2:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("ap_article")
@Schema(description = "文章数据模型")
public class ApArticle {
    /**
     * 主键
     */
    @Schema(description = "文章id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文章标题
     */
    @Schema(description = "文章标题")
    private String title;

    /**
     * 文章作者的ID
     */
    @Schema(description = "文章作者的ID")
    private Long authorId;

    /**
     * 文章作者的昵称
     */
    @Schema(description = "文章作者的昵称")
    private String authorName;

    /**
     * 文章的频道ID
     */
    @Schema(description = "文章的频道ID")
    private Long channelId;

    /**
     * 文章的频道名称
     */
    @Schema(description = "文章的频道名称")
    private String channelName;

    /**
     * 文章的布局：
     * <ul>
     *     <li>0 无图文章</li>
     *     <li>1 单图文章</li>
     *     <li>2 多图文章</li>
     * </ul>
     */
    @Schema(description = "文章的布局，0 无图文章，1 单图文章，2 多图文章")
    private Layout layout;

    /**
     * 文章的标记
     * <ul>
     *     <li>0 普通文章</li>
     *     <li>1 热点文章</li>
     *     <li>2 置顶文章</li>
     *     <li>3 精品文章</li>
     *     <li>4 大V文章</li>
     * </ul>
     */
    @Schema(description = "文章的标记，0 普通文章，1 热点文章，2 置顶文章，3 精品文章，4 大V文章")
    private Flag flag;

    /**
     * 文章的图片，多个图片以逗号分隔
     */
    @Schema(description = "文章的图片，多个图片以逗号分隔")
    private String images;

    /**
     * 文章的标签，多个标签以逗号分隔，最多3个
     */
    @Schema(description = "文章的标签，多个标签以逗号分隔，最多3个")
    private String labels;

    /**
     * 文章的点赞数
     */
    @Schema(description = "文章的点赞数")
    private Integer likes;

    /**
     * 文章的收藏数
     */
    @Schema(description = "文章的收藏数")
    private Integer collection;

    /**
     * 文章的评论数
     */
    @Schema(description = "文章的评论数")
    private Integer comment;

    /**
     * 文章的阅读数
     */
    @Schema(description = "文章的阅读数")
    private Integer views;

    /**
     * 文章的省份ID
     */
    @Schema(description = "文章的省份ID")
    private String provinceId;

    /**
     * 文章的城市ID
     */
    @Schema(description = "文章的城市ID")
    private String cityId;

    /**
     * 文章的区县ID
     */
    @Schema(description = "文章的区县ID")
    private String countyId;

    /**
     * 文章的创建时间
     */
    @Schema(description = "文章的创建时间")
    private LocalDateTime createdTime;

    /**
     * 文章的发布时间
     */
    @Schema(description = "文章的发布时间")
    private LocalDateTime publishTime;

    /**
     * 文章的同步状态
     */
    @Schema(description = "文章的同步状态")
    private Integer syncStatus;

    /**
     * 文章的来源
     */
    @Schema(description = "文章的来源")
    private Integer origin;

    /**
     * 文章的静态页面地址
     */
    @Schema(description = "文章的静态页面地址")
    private String staticUrl;
}
