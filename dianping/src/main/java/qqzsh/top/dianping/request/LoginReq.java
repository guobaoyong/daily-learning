package qqzsh.top.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 17:24
 * @Description 登录用户请求校验
 */
@Data
public class LoginReq {

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

}
