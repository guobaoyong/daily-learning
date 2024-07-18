package com.heima.model.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.user.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * APP用户信息表
 * </p>
 *
 * @author itheima，高翔宇
 */
@Data
@TableName("ap_user")
@Builder
@Schema(description = "APP用户信息表")
public class ApUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;

    /**
     * 密码、通信等加密盐
     */
    @TableField("salt")
    @Schema(description = "密码、通信等加密盐")
    private String salt;

    /**
     * 用户名
     */
    @TableField("name")
    @Schema(description = "用户名")
    private String name;

    /**
     * 密码，已被md5加密
     */
    @TableField("password")
    @Schema(description = "密码，已被md5加密")
    private String password;

    /**
     * 手机号
     */
    @TableField("phone")
    @Schema(description = "手机号")
    private String phone;

    /**
     * 头像
     */
    @TableField("image")
    @Schema(description = "头像")
    private String image;

    /**
     * 0 男
     * 1 女
     * 2 未知
     */
    @TableField("sex")
    @Schema(description = "0 男 1 女 2 未知")
    private Sex sex;

    /**
     * 0 未
     * 1 是
     */
    @TableField("is_certification")
    @Schema(description = "是否实名认证，0 未 1 是")
    private Boolean certification;

    /**
     * 是否身份认证
     */
    @TableField("is_identity_authentication")
    @Schema(description = "是否身份认证")
    private Boolean identityAuthentication;

    /**
     * 0正常
     * 1锁定
     */
    @TableField("status")
    @Schema(description = "0正常 1锁定")
    private Boolean status;

    /**
     * 0 普通用户
     * 1 自媒体人
     * 2 大V
     */
    @TableField("flag")
    @Schema(description = "0 普通用户 1 自媒体人 2 大V")
    private Short flag;

    /**
     * 注册时间
     */
    @TableField("created_time")
    @Schema(description = "注册时间")
    private Date createdTime;

}