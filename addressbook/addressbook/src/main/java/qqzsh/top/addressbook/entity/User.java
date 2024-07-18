package qqzsh.top.addressbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-15 20:23
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    // ID
    private Integer id;

    // 用户名
    private String userName;

    // 密码
    private String password;

}
