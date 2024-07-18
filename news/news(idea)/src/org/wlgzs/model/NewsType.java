package org.wlgzs.model;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-08 14:55
 * @Describe
 */
public class NewsType {
    private int newsTypeId;
    private String typeName;

    public NewsType(String typeName) {
        this.typeName = typeName;
    }

    public NewsType() {
    }

    public int getNewsTypeId() {
        return newsTypeId;
    }

    public void setNewsTypeId(int newsTypeId) {
        this.newsTypeId = newsTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
