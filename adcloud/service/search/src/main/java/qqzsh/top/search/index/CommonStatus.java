package qqzsh.top.search.index;

import lombok.Getter;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 15:16
 * @Description
 */
@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
