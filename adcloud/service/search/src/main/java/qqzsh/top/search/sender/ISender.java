package qqzsh.top.search.sender;

import qqzsh.top.search.mysql.dto.MySqlRowData;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:12
 * @Description
 */
public interface ISender {
    void sender(MySqlRowData rowData);
}
