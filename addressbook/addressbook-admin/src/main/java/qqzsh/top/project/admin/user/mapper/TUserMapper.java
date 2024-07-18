package qqzsh.top.project.admin.user.mapper;

import qqzsh.top.project.admin.user.domain.TUser;
import java.util.List;

/**
 * 普通用户Mapper接口
 * 
 * @author zsh
 * @date 2019-11-17
 */
public interface TUserMapper 
{
    /**
     * 查询普通用户
     * 
     * @param id 普通用户ID
     * @return 普通用户
     */
    public TUser selectTUserById(Long id);

    /**
     * 查询普通用户列表
     * 
     * @param tUser 普通用户
     * @return 普通用户集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增普通用户
     * 
     * @param tUser 普通用户
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改普通用户
     * 
     * @param tUser 普通用户
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 删除普通用户
     * 
     * @param id 普通用户ID
     * @return 结果
     */
    public int deleteTUserById(Long id);

    /**
     * 批量删除普通用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTUserByIds(String[] ids);

    /**
     * 根据用户名查询结果
     * @param userName
     * @return
     */
    TUser findByUserName(String userName);

    /**
     * 获取所有用户总数
     * @return
     */
    Long count();

}
