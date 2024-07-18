package com.heima.api.article;

import com.heima.api.article.fallback.IArticleClientFallbackFactory;
import com.heima.api.config.FeignConfig;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 高翔宇
 * @since 2024/3/5 周二 16:27
 */
@FeignClient(value = "heima-leadnews-article", path = "/api/v1/article", fallbackFactory = IArticleClientFallbackFactory.class, configuration = FeignConfig.class)
public interface IArticleClient {
    @PostMapping(value = "/save")
    ResponseResult<Long> saveArticle(@RequestBody ApArticleDto apArticleDto);
}
