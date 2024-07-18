package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.entity.ApArticle;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.common.dto.ResponseResult;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午2:50
 */
public interface ApArticleService extends IService<ApArticle> {
    /**
     * 加载首页文章
     *
     * @param type 加载类型 1加载更多 2加载最新
     */
    ResponseResult<List<ApArticle>> load(ArticleHomeDto articleHomeDto, Short type);

    /**
     * 保存文章，响应结果中携带文章ID
     */
    ResponseResult<Long> saveArticle(ApArticleDto apArticleDto);
}
