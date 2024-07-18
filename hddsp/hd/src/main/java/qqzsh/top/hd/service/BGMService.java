package qqzsh.top.hd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import qqzsh.top.hd.pojo.BGM;
import qqzsh.top.hd.pojo.vo.BGMVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:17
 * @description
 */
public interface BGMService {

    List<BGM> queryAll();

    BGM queryBgmById(Integer id);

    Integer sum();

    List<BGMVO> queryAll(Page page);

    List<BGMVO> queryAllSelects(Page page,String search_value);

    Integer delete(Integer id);

    Integer update(BGM bgm);

    Integer add(BGM bgm);
}
