package qqzsh.top.hd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.hd.pojo.BGM;
import qqzsh.top.hd.pojo.vo.BGMVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:16
 * @description
 */
public interface BGMMapper extends BaseMapper<BGM> {

    @Select("SELECT bgm.*,users.nickname as nickname " +
            "FROM bgm " +
            "LEFT JOIN users ON bgm.user_id = users.id")
    List<BGMVO> queryAll(Page page);

    @Select("SELECT bgm.*,users.nickname as nickname " +
            "FROM bgm " +
            "LEFT JOIN users ON bgm.user_id = users.id " +
            "where bgm.author like '%${search_value}%' or bgm.name like '%${search_value}%' " +
            "or nickname like '%${search_value}%'")
    List<BGMVO> queryAllSelects(Page page,String search_value);

}
