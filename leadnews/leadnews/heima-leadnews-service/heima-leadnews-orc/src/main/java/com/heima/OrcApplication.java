package com.heima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 高翔宇
 * @since 2024/3/16 周六 上午11:04
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrcApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrcApplication.class, args);
    }
}
