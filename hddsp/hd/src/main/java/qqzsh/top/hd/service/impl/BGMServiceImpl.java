package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import qqzsh.top.hd.mapper.BGMMapper;
import qqzsh.top.hd.pojo.BGM;
import qqzsh.top.hd.pojo.vo.BGMVO;
import qqzsh.top.hd.service.BGMService;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:18
 * @description
 */
@Service
public class BGMServiceImpl extends ServiceImpl<BGMMapper, BGM> implements BGMService {


    @Override
    public List<BGM> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public BGM queryBgmById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Integer sum() {
        return baseMapper.selectCount(null);
    }

    @Override
    public List<BGMVO> queryAll(Page page) {
        return baseMapper.queryAll(page);
    }

    @Override
    public List<BGMVO> queryAllSelects(Page page, String search_value) {
        return baseMapper.queryAllSelects(page,search_value);
    }

    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer update(BGM bgm) {
        return baseMapper.updateById(bgm);
    }

    @Override
    public Integer add(BGM bgm) {
        return baseMapper.insert(bgm);
    }
}
