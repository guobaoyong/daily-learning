package com.heima.model.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.admin.enums.AdminStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员用户信息表
 * </p>
 *
 * @author itheima
 */
@Data
@Builder
@TableName("ad_user")
public class AdUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 登录用户名
     */
    @TableField("name")
    private String name;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 盐
     */
    @TableField("salt")
    private String salt;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField("image")
    private String image;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 状态
     * <ul>
     *     <li>0 暂时不可用</li>
     *     <li>1 永久不可用</li>
     *     <li>9 正常可用</li>
     * </ul>
     */
    @TableField("status")
    private AdminStatus status;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 最后一次登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

}