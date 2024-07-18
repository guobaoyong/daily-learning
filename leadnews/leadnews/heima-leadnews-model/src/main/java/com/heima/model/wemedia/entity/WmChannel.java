package com.heima.model.wemedia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 频道信息表
 *
 * @author 高翔宇
 * @since 2024-02-20, 周二, 11:36
 */
@TableName(value = "wm_channel")
@Data
@Schema(description = "频道信息表")
@Builder
public class WmChannel implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;

    /**
     * 频道名称
     */
    @Schema(description = "频道名称")
    private String name;

    /**
     * 频道描述
     */
    @Schema(description = "频道描述")
    private String description;

    /**
     * 是否默认频道
     */
    @Schema(description = "是否默认频道")
    private Boolean isDefault;

    /**
     * 是否启用
     * <ul>
     *     <li>0：禁用</li>
     *     <li>1：启用</li>
     * </ul>
     */
    @Schema(description = "是否启用，0：禁用，1：启用")
    private Boolean status;

    /**
     * 默认排序
     */
    @Schema(description = "默认排序")
    private Integer ord;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createdTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}