package qqzsh.top.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 17:07
 * @Description 注册用户请求校验
 */
@Data
public class RegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "性别不能为空")
    private Integer gender;
}

