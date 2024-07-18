package com.heima.api.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * @author 高翔宇
 * @since 2024/4/2 周二 下午2:00
 */
public class FeignConfig {
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 3);
    }
}
