package qqzsh.top.hd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.UsersFans;
import qqzsh.top.hd.pojo.UsersReport;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:17
 * @description
 */
public interface UserService extends IService<User> {

    User findByUserName(String userName);

    Integer insert(User user);

    Integer update(User user);

    User findById(Integer id);

    boolean isUserLikeVideo(Integer userId, Integer videoId);

    Integer saveUserFanRelation(Integer userId,Integer fanId);

    Integer isFollow(UsersFans usersFans);

    Integer dontbeyourfans(Integer userId, Integer fanId);

    Integer reportUser(UsersReport usersReport);

    List<User> fandAll(Integer page,Integer limit);

    Integer delete(Integer id);

    boolean updateField(Integer id,String field,String value);

    Integer sum();
}
