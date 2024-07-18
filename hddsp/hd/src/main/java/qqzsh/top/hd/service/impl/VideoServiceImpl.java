package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.hd.mapper.SearchRecordMapper;
import qqzsh.top.hd.mapper.UserMapper;
import qqzsh.top.hd.mapper.UsersLikeVideosMapper;
import qqzsh.top.hd.mapper.VideoMapper;
import qqzsh.top.hd.pojo.*;
import qqzsh.top.hd.pojo.vo.CommentsVO;
import qqzsh.top.hd.pojo.vo.VideoVO;
import qqzsh.top.hd.service.VideoService;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:18
 * @description
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private SearchRecordMapper searchRecordMapper;

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer insert(Video video) {
        Integer maxId = baseMapper.maxId();
        if (maxId != null){
            maxId += 1;
        }else {
            maxId = 1;
        }
        video.setId(maxId);
        baseMapper.insert(video);
        return video.getId();
    }

    @Override
    public Integer update(Integer id, String path) {
        Video video = baseMapper.selectById(id);
        video.setCover_path(path);
        return baseMapper.updateById(video);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<VideoVO> queryVideo(Video video, Integer isSaveRecord, Page page) {
        //保存热搜词
        String desc = video.getVideo_desc();
        if (isSaveRecord != null && isSaveRecord == 1){
            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setContent(desc);
            searchRecordMapper.insert(searchRecord);
        }
        if (desc != null && desc != ""){
            return baseMapper.queryVideoSearch(page,desc);
        }else {
            return baseMapper.queryVideo(page);
        }
    }

    @Override
    public void userLikeVideo(Integer userId, Integer videoId, Integer videoCreaterId) {
        // 1. 保存用户和视频的喜欢点赞关联关系表
        UsersLikeVideos ulv = new UsersLikeVideos();
        ulv.setUser_id(userId);
        ulv.setVideo_id(videoId);
        usersLikeVideosMapper.insert(ulv);

        // 2. 视频喜欢数量累加
        Video video = baseMapper.selectById(videoId);
        video.setLike_counts(video.getLike_counts() + 1);
        baseMapper.updateById(video);

        // 3. 用户受喜欢数量的累加
        User user = userMapper.selectById(videoCreaterId);
        user.setReceive_like_counts(user.getReceive_like_counts() + 1);
        userMapper.updateById(user);
    }

    @Override
    public void userUnLikeVideo(Integer userId, Integer videoId, Integer videoCreaterId) {
        // 1. 删除用户和视频的喜欢点赞关联关系表
        QueryWrapper<UsersLikeVideos> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("video_id",videoId);
        usersLikeVideosMapper.delete(queryWrapper);

        // 2. 视频喜欢数量累减
        Video video = baseMapper.selectById(videoId);
        video.setLike_counts(video.getLike_counts() - 1);
        baseMapper.updateById(video);

        // 3. 用户受喜欢数量的累减
        User user = userMapper.selectById(videoCreaterId);
        user.setReceive_like_counts(user.getReceive_like_counts() - 1);
        userMapper.updateById(user);
    }

    @Override
    public List<VideoVO> listOwn(Page page, Integer userId) {
        return baseMapper.queryOwn(page,userId);
    }

    @Override
    public List<VideoVO> queryMyshowMyLikeVideos(Page page, Integer userId) {
        return baseMapper.queryMyshowMyLikeVideos(page,userId);
    }

    @Override
    public List<VideoVO> queryMyFollowVideos(Page page, Integer userId) {
        return baseMapper.queryMyFollowVideos(page,userId);
    }

    @Override
    public List<CommentsVO> getAllComments(Integer videoId, Page page) {
        return baseMapper.getAllComments(videoId,page);
    }

    @Override
    public List<VideoVO> queryVideoAll(Page<VideoVO> page) {
        return baseMapper.queryVideoAll(page);
    }

    @Override
    public List<VideoVO> queryVideoSelects(Page<VideoVO> page, String search_value) {
        return baseMapper.queryVideoSelects(page,search_value);
    }

    @Override
    public List<VideoVO> queryVideoStatus(Page<VideoVO> page, String search_value) {
        return baseMapper.queryVideoStatus(page,search_value);
    }

    @Override
    public Integer sum() {
        return baseMapper.selectCount(null);
    }

    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer update(Video video) {
        return baseMapper.updateById(video);
    }

    @Override
    public Integer sumBySelect(String value) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        try {
            Integer.parseInt(value);
            queryWrapper.eq("status",Integer.parseInt(value));
        }catch (NumberFormatException e){
            queryWrapper.like("video_desc",value);
        }
        return baseMapper.selectCount(queryWrapper);
    }

}
