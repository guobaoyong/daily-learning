package qqzsh.top.sponsor.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.service.IUserService;
import qqzsh.top.sponsor.vo.CreateUserRequest;
import qqzsh.top.sponsor.vo.CreateUserResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:03
 * @Description 用户Controller
 */
@Slf4j
@RestController
public class UserOPController {

    private final IUserService userService;

    @Autowired
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser(
            @RequestBody CreateUserRequest request) throws AdException {
        log.info("sponsor: createUser -> {}",
                JSON.toJSONString(request));
        return userService.createUser(request);
    }
}

