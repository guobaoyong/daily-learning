package qqzsh.top.hd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.hd.pojo.SearchRecord;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-30 16:45
 * @description
 */
public interface SearchRecordMapper extends BaseMapper<SearchRecord> {

    @Select("select content from search_records group by content video by count(content) desc limit 0,12")
    List<String> getHotwords();
}
