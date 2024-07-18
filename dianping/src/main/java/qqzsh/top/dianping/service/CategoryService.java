package qqzsh.top.dianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.pojo.Category;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 23:44
 * @Description 品类接口
 */
public interface CategoryService extends IService<Category> {

    /**
     * 保存品类
     * @param category
     * @return
     * @throws BusinessException
     */
    Category create(Category category) throws BusinessException;

    /**
     * 获取所有品类
     * @return
     */
    List<Category> selectAll();

}
