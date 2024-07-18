package qqzsh.top.project.admin.user.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.common.utils.text.Convert;
import qqzsh.top.project.admin.user.mapper.TUserMapper;
import qqzsh.top.project.admin.user.domain.TUser;
import qqzsh.top.project.admin.user.service.ITUserService;
/**
 * 普通用户Service业务层处理
 * 
 * @author zsh
 * @date 2019-11-17
 */
@Service
public class TUserServiceImpl implements ITUserService 
{
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询普通用户
     * 
     * @param id 普通用户ID
     * @return 普通用户
     */
    @Override
    public TUser selectTUserById(Long id)
    {
        return tUserMapper.selectTUserById(id);
    }

    /**
     * 查询普通用户列表
     * 
     * @param tUser 普通用户
     * @return 普通用户
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser)
    {
        return tUserMapper.selectTUserList(tUser);
    }

    /**
     * 新增普通用户
     * 
     * @param tUser 普通用户
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser)
    {
        return tUserMapper.insertTUser(tUser);
    }

    /**
     * 修改普通用户
     * 
     * @param tUser 普通用户
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser)
    {
        return tUserMapper.updateTUser(tUser);
    }

    /**
     * 删除普通用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTUserByIds(String ids)
    {
        return tUserMapper.deleteTUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除普通用户信息
     * 
     * @param id 普通用户ID
     * @return 结果
     */
    @Override
    public int deleteTUserById(Long id)
    {
        return tUserMapper.deleteTUserById(id);
    }

    @Override
    public TUser findByUserName(String userName) {
        return tUserMapper.findByUserName(userName);
    }

    @Override
    public Long count() {
        return tUserMapper.count();
    }
}
