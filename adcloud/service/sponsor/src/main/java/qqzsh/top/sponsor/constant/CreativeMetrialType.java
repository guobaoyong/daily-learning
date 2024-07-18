package qqzsh.top.sponsor.constant;

import lombok.Getter;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:47
 * @Description 创意格式类型
 */
@Getter
public enum CreativeMetrialType {

    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMetrialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
