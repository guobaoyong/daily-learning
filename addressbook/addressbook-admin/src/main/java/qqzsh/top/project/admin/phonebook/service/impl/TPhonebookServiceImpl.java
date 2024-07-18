package qqzsh.top.project.admin.phonebook.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.common.utils.text.Convert;
import qqzsh.top.project.admin.phonebook.mapper.TPhonebookMapper;
import qqzsh.top.project.admin.phonebook.domain.TPhonebook;
import qqzsh.top.project.admin.phonebook.service.ITPhonebookService;

/**
 * 通讯录Service业务层处理
 * 
 * @author zsh
 * @date 2019-11-17
 */
@Service
public class TPhonebookServiceImpl implements ITPhonebookService 
{
    @Autowired
    private TPhonebookMapper tPhonebookMapper;

    /**
     * 查询通讯录
     * 
     * @param id 通讯录ID
     * @return 通讯录
     */
    @Override
    public TPhonebook selectTPhonebookById(Long id)
    {
        return tPhonebookMapper.selectTPhonebookById(id);
    }

    /**
     * 查询通讯录列表
     * 
     * @param tPhonebook 通讯录
     * @return 通讯录
     */
    @Override
    public List<TPhonebook> selectTPhonebookList(TPhonebook tPhonebook)
    {
        return tPhonebookMapper.selectTPhonebookList(tPhonebook);
    }

    /**
     * 新增通讯录
     * 
     * @param tPhonebook 通讯录
     * @return 结果
     */
    @Override
    public int insertTPhonebook(TPhonebook tPhonebook)
    {
        return tPhonebookMapper.insertTPhonebook(tPhonebook);
    }

    /**
     * 修改通讯录
     * 
     * @param tPhonebook 通讯录
     * @return 结果
     */
    @Override
    public int updateTPhonebook(TPhonebook tPhonebook)
    {
        return tPhonebookMapper.updateTPhonebook(tPhonebook);
    }

    /**
     * 删除通讯录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTPhonebookByIds(String ids)
    {
        return tPhonebookMapper.deleteTPhonebookByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除通讯录信息
     * 
     * @param id 通讯录ID
     * @return 结果
     */
    @Override
    public int deleteTPhonebookById(Long id)
    {
        return tPhonebookMapper.deleteTPhonebookById(id);
    }

    @Override
    public Long count() {
        return tPhonebookMapper.count();
    }
}
