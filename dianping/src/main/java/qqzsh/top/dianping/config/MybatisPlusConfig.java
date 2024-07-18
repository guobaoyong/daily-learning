package qqzsh.top.dianping.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-12-11 14:59
 * @description MyBatis plus 分页插件配置
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 1.分页插件
     * 2.多租户
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
