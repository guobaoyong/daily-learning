package com.heima.admin.config;

import com.heima.admin.interceptor.AdminInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:29
 */
@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/**");
    }
}
