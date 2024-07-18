package qqzsh.top.search.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qqzsh.top.search.mysql.constant.OpType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:06
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlRowData {

    private String tableName;

    private String level;

    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
