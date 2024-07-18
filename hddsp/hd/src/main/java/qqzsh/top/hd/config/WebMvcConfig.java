package qqzsh.top.hd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import qqzsh.top.hd.controller.MiniInterceptor;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 16:02
 * @description
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源的加载和Swagger2配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:./res/")
                 .addResourceLocations("classpath:/static/");
    }

    /**
     * 拦截器
     * @return
     */
    @Bean
    public MiniInterceptor miniInterceptor(){
        return new MiniInterceptor();
    }


    /**
     * 通过自定义拦截器拦截 user、video、bgm的请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
                .addPathPatterns("/video/upload", "/video/uploadCover",
                        "/video/userLike", "/video/userUnLike",
                        "/video/saveComment")
                .addPathPatterns("/bgm/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/user/queryPublisher")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/judge/**");
    }
}
