package qqzsh.top.project.admin.phonebook.mapper;

import qqzsh.top.project.admin.phonebook.domain.TPhonebook;
import java.util.List;

/**
 * 通讯录Mapper接口
 * 
 * @author zsh
 * @date 2019-11-17
 */
public interface TPhonebookMapper 
{
    /**
     * 查询通讯录
     * 
     * @param id 通讯录ID
     * @return 通讯录
     */
    public TPhonebook selectTPhonebookById(Long id);

    /**
     * 查询通讯录列表
     * 
     * @param tPhonebook 通讯录
     * @return 通讯录集合
     */
    public List<TPhonebook> selectTPhonebookList(TPhonebook tPhonebook);

    /**
     * 新增通讯录
     * 
     * @param tPhonebook 通讯录
     * @return 结果
     */
    public int insertTPhonebook(TPhonebook tPhonebook);

    /**
     * 修改通讯录
     * 
     * @param tPhonebook 通讯录
     * @return 结果
     */
    public int updateTPhonebook(TPhonebook tPhonebook);

    /**
     * 删除通讯录
     * 
     * @param id 通讯录ID
     * @return 结果
     */
    public int deleteTPhonebookById(Long id);

    /**
     * 批量删除通讯录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPhonebookByIds(String[] ids);

    /**
     * 获取所有通讯录总数
     * @return
     */
    Long count();
}
