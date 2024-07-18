package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import qqzsh.top.hd.mapper.SearchRecordMapper;
import qqzsh.top.hd.pojo.SearchRecord;
import qqzsh.top.hd.service.SearchRecordService;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-30 16:46
 * @description
 */
@Service
public class SearchRecordServiceImpl extends ServiceImpl<SearchRecordMapper, SearchRecord> implements SearchRecordService {
    @Override
    public List<String> getHotwords() {
        return baseMapper.getHotwords();
    }
}
