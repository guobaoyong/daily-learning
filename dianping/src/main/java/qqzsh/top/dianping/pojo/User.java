package qqzsh.top.dianping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-11 15:38
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    //主键ID
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //创建时间
    @TableField("created_at")
    private LocalDateTime createdAt;

    //更新时间
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    //手机号码
    @TableField("telphone")
    private String telphone;

    //密码
    @TableField("password")
    private String password;

    //昵称
    @TableField("nick_name")
    private String nickName;

    //性别
    @TableField("gender")
    private Integer gender;

    //角色 user-admin
    @TableField("role")
    private String role;

}
