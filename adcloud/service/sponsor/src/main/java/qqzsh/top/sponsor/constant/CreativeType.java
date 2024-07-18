package qqzsh.top.sponsor.constant;

import lombok.Getter;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:46
 * @Description 创意类型
 */
@Getter
public enum CreativeType {

    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
