package qqzsh.top.hd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.hd.pojo.Comments;
import qqzsh.top.hd.pojo.vo.CommentsVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-31 11:26
 * @description
 */
public interface CommentsMapper extends BaseMapper<Comments> {

    @Select("SELECT c.*,u.face_image as face_image,u.nickname as nickname,tu.nickname as toNickname FROM comments c LEFT JOIN users u on c.from_user_id = u.id " +
            "LEFT JOIN users tu on c.to_user_id = tu.id " +
            "ORDER BY c.create_time DESC")
    List<CommentsVO> getAllComments(Page page);

    @Select("SELECT c.*,u.face_image as face_image,u.nickname as nickname,tu.nickname as toNickname FROM comments c LEFT JOIN users u on c.from_user_id = u.id " +
            "LEFT JOIN users tu on c.to_user_id = tu.id " +
            "where c.comment like '%${search_value}%' ORDER BY c.create_time DESC")
    List<CommentsVO> getAllCommentsSelects(Page page, String search_value);
}
