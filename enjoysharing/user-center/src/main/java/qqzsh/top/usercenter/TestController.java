package qqzsh.top.usercenter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.usercenter.dao.user.UserMapper;
import qqzsh.top.usercenter.domain.entity.user.User;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-07 21:25
 * @Description
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    /**
     * 认为是Null
     * 1. @Autowired(required = false)
     * 2. @Resource代替@Autowired
     * 3. 在mapper接口中加入@Repository
     * 4. 使用@RequiredArgsConstructor
     */
    private final UserMapper userMapper;

    @GetMapping("/test")
    public User testInsert() {
        User user = new User();
        user.setAvatarUrl("xxx");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        this.userMapper.insertSelective(user);
        return user;
    }

    // q?id=1&wxId=aaa&...
    @GetMapping("/q")
    public User query(User user) {
        return user;
    }



}
