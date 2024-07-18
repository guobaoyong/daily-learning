package qqzsh.top.soufun.service;

import qqzsh.top.soufun.entity.Role;
import qqzsh.top.soufun.entity.User;
import qqzsh.top.soufun.web.dto.UserDTO;
import qqzsh.top.soufun.web.form.UserForm;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 10:17
 * @description 用户服务
 */
public interface IUserService {

    User findUserByName(String userName);

    ServiceResult<UserDTO> findById(Long userId);

    List<User> findAll();

    /**
     * 根据电话号码寻找用户
     * @param telephone
     * @return
     */
    User findUserByTelephone(String telephone);

    /**
     * 通过手机号注册用户
     * @param telehone
     * @return
     */
    User addUserByPhone(String telehone);

    /**
     * 修改指定属性值
     * @param profile
     * @param value
     * @return
     */
    ServiceResult modifyUserProfile(String profile, String value);

    /**
     * 新增用户
     * @param userForm
     */
    void save(UserForm userForm);

    /**
     * 更新用户
     */
    void update(UserForm userForm);

    /**
     * 根据用户id查询角色信息
     */
    Role findByUserId(Long userId);

    /**
     * 删除用户
     */
    ServiceResult deleteUser(Long id);

    /**
     * 根据角色Id修改角色状态
     */
    ServiceResult updateStatus(Long userId,int status);
}
