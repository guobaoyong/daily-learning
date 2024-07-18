package qqzsh.top.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 23:53
 * @Description 品类注册
 */
@Data
public class CategoryCreateReq {

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "iconUrl不能为空")
    private String iconUrl;

    @NotNull(message = "权重不能为空")
    private Integer sort;
}

