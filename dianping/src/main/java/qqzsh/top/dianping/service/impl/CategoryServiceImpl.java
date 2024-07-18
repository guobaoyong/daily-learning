package qqzsh.top.dianping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.mapper.CategoryMapper;
import qqzsh.top.dianping.pojo.Category;
import qqzsh.top.dianping.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 23:45
 * @Description 品类服务实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    @Transactional
    public Category create(Category category) throws BusinessException {
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        try{
            super.save(category);
        }catch(DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }
        return category;
    }

    @Override
    public List<Category> selectAll() {
        return baseMapper.selectList(null);
    }
}
