package com.heima.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.wemedia.entity.WmNewsMaterial;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 下午5:10
 */
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {
    /**
     * 批量插入
     */
    void insertBatch(List<WmNewsMaterial> wmNewsMaterials);
}
