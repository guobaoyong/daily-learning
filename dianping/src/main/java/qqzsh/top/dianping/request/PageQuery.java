package qqzsh.top.dianping.request;

import lombok.Data;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:47
 * @Description 分页
 */
@Data
public class PageQuery {

    private Integer page = 1;

    private Integer size = 20;

}
