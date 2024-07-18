package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:14
 * @Description 创建用户响应VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    //用户ID
    private Long userId;
    //用户名
    private String username;
    //用户token
    private String token;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
