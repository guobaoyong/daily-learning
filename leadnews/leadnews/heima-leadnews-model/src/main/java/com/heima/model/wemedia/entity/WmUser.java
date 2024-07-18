package com.heima.model.wemedia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.wemedia.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 自媒体用户信息表
 * </p>
 *
 * @author itheima，高翔宇
 */
@Data
@Builder
@TableName("wm_user")
@Schema(description = "自媒体用户信息表")
public class WmUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    @TableField("ap_user_id")
    @Schema(description = "自媒体用户ID")
    private Integer apUserId;

    @TableField("ap_author_id")
    @Schema(description = "自媒体作者ID")
    private Integer apAuthorId;

    /**
     * 登录用户名
     */
    @TableField("name")
    @Schema(description = "登录用户名")
    private String name;

    /**
     * 登录密码
     */
    @TableField("password")
    @Schema(description = "登录密码")
    private String password;

    /**
     * 盐
     */
    @TableField("salt")
    @Schema(description = "盐")
    private String salt;

    /**
     * 昵称
     */
    @TableField("nickname")
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @TableField("image")
    @Schema(description = "头像")
    private String image;

    /**
     * 归属地
     */
    @TableField("location")
    @Schema(description = "归属地")
    private String location;

    /**
     * 手机号
     */
    @TableField("phone")
    @Schema(description = "手机号")
    private String phone;

    /**
     * 状态
     * 0 暂时不可用
     * 1 永久不可用
     * 9 正常可用
     */
    @TableField("status")
    @Schema(description = "状态，0：暂时不可用，1：永久不可用，9：正常可用")
    private UserStatus status;

    /**
     * 邮箱
     */
    @TableField("email")
    @Schema(description = "邮箱")
    private String email;

    /**
     * 账号类型
     * 0 个人
     * 1 企业
     * 2 子账号
     */
    @TableField("type")
    @Schema(description = "账号类型，0：个人，1：企业，2：子账号")
    private Integer type;

    /**
     * 运营评分
     */
    @TableField("score")
    @Schema(description = "运营评分")
    private Integer score;

    /**
     * 最后一次登录时间
     */
    @TableField("login_time")
    @Schema(description = "最后一次登录时间")
    private Date loginTime;

    /**
     * 创建时间
     */
    @TableField("created_time")
    @Schema(description = "创建时间")
    private Date createdTime;
}