package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.hd.mapper.CommentsMapper;
import qqzsh.top.hd.mapper.UserMapper;
import qqzsh.top.hd.mapper.UsersFansMapper;
import qqzsh.top.hd.mapper.UsersLikeVideosMapper;
import qqzsh.top.hd.pojo.Comments;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.UsersFans;
import qqzsh.top.hd.pojo.UsersLikeVideos;
import qqzsh.top.hd.pojo.vo.CommentsVO;
import qqzsh.top.hd.service.CommentsService;
import qqzsh.top.hd.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:18
 * @description
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {


    @Override
    public Integer insert(Comments comments) {
        comments.setCreate_time(new Date());
        return baseMapper.insert(comments);
    }

    @Override
    public List<CommentsVO> getAllComments(Page page) {
        return baseMapper.getAllComments(page);
    }

    @Override
    public List<CommentsVO> getAllCommentsSelects(Page page, String search_value) {
        return baseMapper.getAllCommentsSelects(page,search_value);
    }

    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer selectCount(String search_value) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        if (search_value != null){
            queryWrapper.like("comment",search_value);
        }
        return baseMapper.selectCount(queryWrapper);
    }
}
