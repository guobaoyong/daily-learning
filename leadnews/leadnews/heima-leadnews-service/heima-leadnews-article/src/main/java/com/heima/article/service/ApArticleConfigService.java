package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.entity.ApArticleConfig;

import java.util.Map;

/**
 * @author 高翔宇
 * @since 2024/3/5 周二 16:03
 */
public interface ApArticleConfigService extends IService<ApArticleConfig> {
    /**
     * 根据map更新
     */
    void updateByMapByArticleId(Map<String, String> map);
}
