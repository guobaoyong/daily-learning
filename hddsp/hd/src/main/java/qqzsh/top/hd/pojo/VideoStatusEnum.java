package qqzsh.top.hd.pojo;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 21:04
 * @description
 */
public enum VideoStatusEnum {

    SUCCESS(1),		// 发布成功
    FORBID(2);		// 禁止播放，管理员操作

    public final int value;

    VideoStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

