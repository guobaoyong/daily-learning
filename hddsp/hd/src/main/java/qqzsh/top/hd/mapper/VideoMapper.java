package qqzsh.top.hd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.hd.pojo.vo.CommentsVO;
import qqzsh.top.hd.pojo.Video;
import qqzsh.top.hd.pojo.vo.VideoVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 17:59
 * @description
 */
public interface VideoMapper extends BaseMapper<Video> {

    @Select("SELECT MAX(id) FROM videos")
    Integer maxId();

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " where v.status = 1 order by v.create_time desc")
    List<VideoVO> queryVideo(Page<VideoVO> page);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " order by v.create_time desc")
    List<VideoVO> queryVideoAll(Page<VideoVO> page);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " where v.video_desc like '%${search_value}%' order by v.create_time desc")
    List<VideoVO> queryVideoSelects(Page<VideoVO> page,String search_value);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " where v.status = ${search_value} order by v.create_time desc")
    List<VideoVO> queryVideoStatus(Page<VideoVO> page,String search_value);


    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " where v.status = 1 and v.user_id = ${userId} order by v.create_time desc")
    List<VideoVO> queryOwn(Page<VideoVO> page,Integer userId);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname" +
            " from videos v left join users u on u.id = v.user_id" +
            " where v.status = 1 and v.video_desc like '%${video_desc}%' order by v.create_time desc")
    List<VideoVO> queryVideoSearch(Page<VideoVO> page, @Param("video_desc") String video_desc);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname from videos v left join users u on u.id = v.user_id " +
            "WHERE v.id in (select us.video_id from users_like_videos us left join videos vi on  us.video_id = vi.id WHERE us.user_id = ${userId}) " +
            "order by create_time desc")
    List<VideoVO> queryMyshowMyLikeVideos(Page page,Integer userId);

    @Select("select v.*,u.face_image as face_image,u.nickname as nickname from videos v left join users u on u.id = v.user_id " +
            "WHERE v.user_id in (select us.fan_id from users_fans us left join videos vi on us.fan_id = vi.user_id WHERE us.user_id = ${userId}) " +
            "order by create_time desc")
    List<VideoVO> queryMyFollowVideos(Page page,Integer userId);

    @Select("SELECT c.*,u.face_image as face_image,u.nickname as nickname,tu.nickname as toNickname FROM comments c LEFT JOIN users u on c.from_user_id = u.id " +
            "LEFT JOIN users tu on c.to_user_id = tu.id " +
            "WHERE c.video_id = ${videoId} ORDER BY c.create_time DESC")
    List<CommentsVO> getAllComments(Integer videoId, Page page);




}
