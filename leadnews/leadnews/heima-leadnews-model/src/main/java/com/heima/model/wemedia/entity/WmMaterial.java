package com.heima.model.wemedia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.wemedia.enums.MaterialIsCollection;
import com.heima.model.wemedia.enums.MaterialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 自媒体图文素材信息表
 *
 * @author itheima，高翔宇
 */
@Data
@Builder
@TableName("wm_material")
@Schema(description = "自媒体图文素材信息表")
public class WmMaterial implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 自媒体用户ID
     */
    @TableField("user_id")
    @Schema(description = "自媒体用户ID")
    private Long userId;

    /**
     * 图片地址
     */
    @TableField("url")
    @Schema(description = "图片地址")
    private String url;

    /**
     * 素材类型
     * 0 图片
     * 1 视频
     */
    @TableField("type")
    @Schema(description = "素材类型，0：图片，1：视频")
    private MaterialType type;

    /**
     * 是否收藏
     * <ul>
     *     <li>0: 否</li>
     *     <li>1: 是</li>
     * </ul>
     */
    @TableField("is_collection")
    @Schema(description = "是否收藏，0：否，1：是")
    private MaterialIsCollection isCollection;

    /**
     * 创建时间
     */
    @TableField("created_time")
    @Schema(description = "创建时间")
    private Date createdTime;
}