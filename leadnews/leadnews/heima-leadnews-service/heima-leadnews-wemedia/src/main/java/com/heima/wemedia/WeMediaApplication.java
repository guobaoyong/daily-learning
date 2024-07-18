package com.heima.wemedia;

import com.heima.api.config.FallbackConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 高翔宇
 * @since 2024/2/15 周四 下午4:07
 */
@SpringBootApplication
@MapperScan("com.heima.wemedia.mapper")
@EnableAsync()
@EnableFeignClients(basePackages = "com.heima.api", defaultConfiguration = FallbackConfig.class)
@EnableScheduling
@EnableCaching
public class WeMediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeMediaApplication.class, args);
    }
}
