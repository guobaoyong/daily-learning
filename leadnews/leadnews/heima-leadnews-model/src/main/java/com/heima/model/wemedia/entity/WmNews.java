package com.heima.model.wemedia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heima.model.common.enums.Layout;
import com.heima.model.wemedia.enums.NewsEnable;
import com.heima.model.wemedia.enums.NewsStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 上午10:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "自媒体图文内容信息表数据模型")
@TableName("wm_news")
public class WmNews implements Serializable {
    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @TableField("user_id")
    private Long userId;
    /**
     * 文章标题
     */
    @Schema(description = "文章标题")
    @TableField("title")
    private String title;
    /**
     * 图文内容
     */
    @Schema(description = "图文内容")
    @TableField("content")
    private String content;
    /**
     * 文章类型
     * <ul>
     *     <li>0: 无图文章</li>
     *     <li>1: 单图文章</li>
     *     <li>3: 多图文章</li>
     * </ul>
     */
    @Schema(description = "文章类型")
    @TableField("type")
    private Layout type;
    /**
     * 图文频道ID
     */
    @Schema(description = "图文频道ID")
    @TableField("channel_id")
    private Long channelId;
    /**
     * 文章的标签，多个标签以逗号分隔，最多3个
     */
    @Schema(description = "文章的标签，多个标签以逗号分隔，最多3个")
    @TableField("labels")
    private String labels;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField("created_time")
    private Date createdTime;
    /**
     * 提交时间
     */
    @Schema(description = "提交时间")
    @TableField("submitted_time")
    @JsonProperty("submitedTime")
    private Date submittedTime;
    /**
     * 当前状态
     * <ul>
     *     <li>0: 草稿</li>
     *     <li>1: 提交（待审核）</li>
     *     <li>2: 审核失败</li>
     *     <li>3: 人工审核</li>
     *     <li>4: 审核通过（待发布）</li>
     *     <li>9: 已发布</li>
     * </ul>
     */
    @Schema(description = "当前状态，0: 草稿，1: 提交（待审核），2: 审核失败，3: 人工审核，4: 审核通过（待发布），9: 已发布")
    @TableField("status")
    private NewsStatus status;
    /**
     * 定时发布时间，不定时发布则为空
     */
    @Schema(description = "定时发布时间，不定时发布则为空")
    @TableField("publish_time")
    private Date publishTime;
    /**
     * 拒绝理由
     */
    @Schema(description = "拒绝理由")
    @TableField("reason")
    private String reason;
    /**
     * 发布库文章ID
     */
    @Schema(description = "发布库文章ID")
    @TableField("article_id")
    private Long articleId;
    /**
     * 图片地址，多个图片以逗号分隔
     */
    @Schema(description = "图片地址，多个图片以逗号分隔")
    @TableField("images")
    private String images;
    /**
     * 是否可用，默认为1
     * <ul>
     *     <li>0: 不可用</li>
     *     <li>1: 可用</li>
     * </ul>
     */
    @Schema(description = "是否可用，默认为1，0: 不可用，1: 可用")
    @TableField("enable")
    private NewsEnable enable;
}
