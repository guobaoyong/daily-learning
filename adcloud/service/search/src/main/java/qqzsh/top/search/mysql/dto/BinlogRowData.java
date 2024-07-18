package qqzsh.top.search.mysql.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

import com.github.shyiko.mysql.binlog.event.EventType;
/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:05
 * @Description
 */
@Data
public class BinlogRowData {

    private TableTemplate table;

    private EventType eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
