package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import qqzsh.top.hd.mapper.UsersReportMapper;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.UsersReport;
import qqzsh.top.hd.pojo.vo.UserVO;
import qqzsh.top.hd.pojo.vo.UsersReportVO;
import qqzsh.top.hd.service.UserReportService;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-04 20:07
 * @description
 */
@Service
public class UserReportServiceImpl extends ServiceImpl<UsersReportMapper, UsersReport> implements UserReportService {
    @Override
    public List<UsersReportVO> queryAll(Page page) {
        return baseMapper.queryAll(page);
    }

    @Override
    public Integer update(Integer id, String field, String value) {
        QueryWrapper<UsersReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(field,field);
        UsersReport report = baseMapper.selectById(id);
        report.setStatus(Integer.parseInt(value));
        return baseMapper.updateById(report);
    }

    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer sum() {
        return baseMapper.selectCount(null);
    }

    @Override
    public Integer sumBySelect(String value) {
        QueryWrapper<UsersReport> queryWrapper = new QueryWrapper<>();
        try {
            Integer.parseInt(value);
            queryWrapper.eq("status",Integer.parseInt(value));
        }catch (NumberFormatException e){
            queryWrapper.like("title",value).or().like("content",value);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<UsersReportVO> queryAllBySelect(Page page, String value) {
        return baseMapper.queryAllBySelect(page,value);
    }

    @Override
    public List<UsersReportVO> queryAllByStatus(Page page, String value) {
        return baseMapper.queryAllByStatus(page,value);
    }
}
