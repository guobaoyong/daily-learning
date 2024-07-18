package qqzsh.top.search.mysql.listener;

import qqzsh.top.search.mysql.dto.BinlogRowData;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:10
 * @Description
 */
public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
