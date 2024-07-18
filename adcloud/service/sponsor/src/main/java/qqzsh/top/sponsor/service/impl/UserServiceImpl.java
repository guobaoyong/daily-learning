package qqzsh.top.sponsor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.constant.Constants;
import qqzsh.top.sponsor.dao.AdUserRepository;
import qqzsh.top.sponsor.entity.AdUser;
import qqzsh.top.sponsor.service.IUserService;
import qqzsh.top.sponsor.utils.CommonUtils;
import qqzsh.top.sponsor.vo.CreateUserRequest;
import qqzsh.top.sponsor.vo.CreateUserResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:16
 * @Description 用户IUserService实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //创建用户
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request)
            throws AdException {

        //用户名是否为空
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //查重
        AdUser oldUser = userRepository.
                findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        //保存数据库
        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));

        //响应前台
        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }
}

