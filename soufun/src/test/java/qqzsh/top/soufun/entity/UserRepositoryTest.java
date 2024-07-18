package qqzsh.top.soufun.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qqzsh.top.soufun.ApplicationTests;
import qqzsh.top.soufun.repository.UserRepository;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-29 17:27
 * @description UserRepository测试用例
 */
public class UserRepositoryTest extends ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(1L);
        Assert.assertEquals("wali", user.getName());
    }

}












