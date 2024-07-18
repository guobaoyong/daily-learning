package qqzsh.top.hd.utils;

import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-03 21:03
 * @description
 */
@Data
public class LayUIResponse<T> {
    private Integer code;
    private String msg;
    private Long count;
    private T data;

    public LayUIResponse(Integer code, String msg, Long count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
