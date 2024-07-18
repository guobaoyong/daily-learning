package com.heima.search.config;

import com.heima.search.interceptor.ApArticleInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 下午4:19
 */
@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(new ApArticleInterceptor()).addPathPatterns("/**");
    }
}
