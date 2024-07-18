package qqzsh.top.hd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import qqzsh.top.hd.pojo.vo.CommentsVO;
import qqzsh.top.hd.pojo.Video;
import qqzsh.top.hd.pojo.vo.VideoVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:17
 * @description
 */
public interface VideoService extends IService<Video> {

    Integer insert(Video video);

    Integer update(Integer id,String path);

    List<VideoVO> queryVideo(Video video,Integer isSaveRecord,Page page);

    void userLikeVideo(Integer userId, Integer videoId, Integer videoCreaterId);

    void userUnLikeVideo(Integer userId, Integer videoId, Integer videoCreaterId);

    List<VideoVO> listOwn(Page page,Integer userId);

    List<VideoVO> queryMyshowMyLikeVideos(Page page,Integer userId);

    List<VideoVO> queryMyFollowVideos(Page page,Integer userId);

    List<CommentsVO> getAllComments(Integer videoId, Page page);

    List<VideoVO> queryVideoAll(Page<VideoVO> page);

    List<VideoVO> queryVideoSelects(Page<VideoVO> page,String search_value);

    List<VideoVO> queryVideoStatus(Page<VideoVO> page,String search_value);

    Integer sum();

    Integer delete(Integer id);

    Integer update(Video video);

    Integer sumBySelect(String value);

}
