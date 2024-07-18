package qqzsh.top.soufun.web.dto;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 11:38
 * @description
 */
public class QiNiuPutRet {

    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
