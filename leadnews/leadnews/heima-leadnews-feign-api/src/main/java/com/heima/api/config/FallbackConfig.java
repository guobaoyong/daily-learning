package com.heima.api.config;

import com.heima.api.article.fallback.IArticleClientFallbackFactory;
import com.heima.api.schedule.fallback.ScheduleFallbackFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author 高翔宇
 * @since 2024/3/8 周五 13:13
 */
public class FallbackConfig {
    @Bean
    public IArticleClientFallbackFactory iArticleClientFallbackFactory() {
        return new IArticleClientFallbackFactory();
    }

    @Bean
    public ScheduleFallbackFactory scheduleFallbackFactory() {
        return new ScheduleFallbackFactory();
    }
}
