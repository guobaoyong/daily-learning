package com.heima.api.article.fallback;

import com.heima.api.article.IArticleClient;
import com.heima.model.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author 高翔宇
 * @since 2024/3/9 周六 下午12:31
 */
public class IArticleClientFallbackFactory implements FallbackFactory<IArticleClient> {
    @Override
    public IArticleClient create(Throwable throwable) {
        return apArticleDto -> ResponseResult.errorResult(503, throwable.getMessage());
    }
}
