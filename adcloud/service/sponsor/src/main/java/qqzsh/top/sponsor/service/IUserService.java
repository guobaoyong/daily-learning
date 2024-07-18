package qqzsh.top.sponsor.service;

import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.vo.CreateUserRequest;
import qqzsh.top.sponsor.vo.CreateUserResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:12
 * @Description 用户接口
 */
public interface IUserService {

    //创建用户
    CreateUserResponse createUser(CreateUserRequest request)
            throws AdException;
}
