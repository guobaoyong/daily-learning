package qqzsh.top.sponsor.constant;

import lombok.Getter;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:19
 * @Description 状态常量枚举
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
