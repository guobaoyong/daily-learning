package qqzsh.top.hd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 17:29
 * @description
 */
@Data
@TableName("search_records")
public class SearchRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
}
