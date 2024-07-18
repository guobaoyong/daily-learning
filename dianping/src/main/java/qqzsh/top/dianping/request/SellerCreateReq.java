package qqzsh.top.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:46
 * @Description 添加商家
 */
@Data
public class SellerCreateReq {

    @NotBlank(message = "商户名不能为空")
    private String name;

}
