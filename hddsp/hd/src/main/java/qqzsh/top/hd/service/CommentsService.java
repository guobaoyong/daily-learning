package qqzsh.top.hd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import qqzsh.top.hd.pojo.Comments;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.UsersFans;
import qqzsh.top.hd.pojo.vo.CommentsVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:17
 * @description
 */
public interface CommentsService {

    Integer insert(Comments comments);

    List<CommentsVO> getAllComments(Page page);

    List<CommentsVO> getAllCommentsSelects(Page page,String search_value);

    Integer delete(Integer id);

    Integer selectCount(String search_value);
}
