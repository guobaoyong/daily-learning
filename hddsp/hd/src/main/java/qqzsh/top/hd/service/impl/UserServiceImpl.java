package qqzsh.top.hd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.hd.mapper.UserMapper;
import qqzsh.top.hd.mapper.UsersFansMapper;
import qqzsh.top.hd.mapper.UsersLikeVideosMapper;
import qqzsh.top.hd.mapper.UsersReportMapper;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.UsersFans;
import qqzsh.top.hd.pojo.UsersLikeVideos;
import qqzsh.top.hd.pojo.UsersReport;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UsersFansMapper usersFansMapper;

    @Autowired
    private UsersReportMapper usersReportMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer insert(User user) {
        if (user.getRole() == null){
            user.setRole(1);
        }
        return baseMapper.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer update(User user) {
        return baseMapper.updateById(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User findById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUserLikeVideo(Integer userId, Integer videoId) {
        if (userId == null || videoId == null) {
            return false;
        }
        QueryWrapper<UsersLikeVideos> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("video_id",videoId);
        List<UsersLikeVideos> list = usersLikeVideosMapper.selectList(queryWrapper);
        if (list != null && list.size() >0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer saveUserFanRelation(Integer userId, Integer fanId) {
        //1.给用户粉丝记录表添加数据
        UsersFans usersFans = new UsersFans();
        usersFans.setUser_id(userId);
        usersFans.setFan_id(fanId);
        //2.给自己的关注数+1
        User user = baseMapper.selectById(userId);
        user.setFollow_counts(user.getFollow_counts() + 1);
        baseMapper.updateById(user);
        //3.给被关注的粉丝数+1
        User user1 = baseMapper.selectById(fanId);
        user1.setFollow_counts(user1.getFans_counts() + 1);
        baseMapper.updateById(user1);
        return usersFansMapper.insert(usersFans);
    }

    @Override
    public Integer dontbeyourfans(Integer userId, Integer fanId) {
        //1.给用户粉丝记录表删除数据
        QueryWrapper<UsersFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("fan_id",fanId);
        //2.给自己的关注数-1
        User user = baseMapper.selectById(userId);
        user.setFollow_counts(user.getFollow_counts() - 1);
        baseMapper.updateById(user);
        //3.给被关注的粉丝数-1
        User user1 = baseMapper.selectById(fanId);
        user1.setFollow_counts(user1.getFans_counts() - 1);
        baseMapper.updateById(user1);
        return usersFansMapper.delete(queryWrapper);
    }

    @Override
    public Integer reportUser(UsersReport usersReport) {
        usersReport.setStatus(0);
        usersReport.setCreate_date(new Date());
        return usersReportMapper.insert(usersReport);
    }

    @Override
    public List<User> fandAll(Integer page,Integer limit) {
        return baseMapper.selectList(null);
    }

    @Override
    public Integer delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public boolean updateField(Integer id, String field, String value) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(field,field);
        baseMapper.update(baseMapper.selectById(id),queryWrapper);
        return true;
    }

    @Override
    public Integer sum() {
        return baseMapper.selectCount(null);
    }


    @Override
    public Integer isFollow(UsersFans usersFans) {
        QueryWrapper<UsersFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",usersFans.getUser_id());
        queryWrapper.eq("fan_id",usersFans.getFan_id());
        if (usersFansMapper.selectOne(queryWrapper) != null){
            return 1;
        }else {
            return 0;
        }
    }
}
