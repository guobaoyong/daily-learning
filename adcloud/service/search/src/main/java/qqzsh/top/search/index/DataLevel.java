package qqzsh.top.search.index;

import lombok.Getter;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 15:15
 * @Description
 */
@Getter
public enum DataLevel {

    LEVEL2("2", "level 2"),
    LEVEL3("3", "level 3"),
    LEVEL4("4", "level 4");

    private String level;
    private String desc;

    DataLevel(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }
}
