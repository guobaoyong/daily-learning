package qqzsh.top.sponsor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qqzsh.top.sponsor.constant.CommonStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:14
 * @Description 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {

    //自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //用户名
    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    //给用户生成的 token
    @Basic
    @Column(name = "token", nullable = false)
    private String token;

    //用户状态
    @Basic
    @Column(name = "user_status", nullable = false)
    private Integer userStatus;

    //创建时间
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    //更新时间
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}

