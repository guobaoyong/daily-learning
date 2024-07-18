package qqzsh.top.search.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qqzsh.top.search.mysql.constant.OpType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:04
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * 字段索引 -> 字段名
     * */
    private Map<Integer, String> posMap = new HashMap<>();
}
