package com.heima.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * &#064;Description:
 * &#064;Version:  V1.0
 * @author itheima
 */
@SpringBootApplication
@MapperScan("com.heima.es.mapper")
public class EsInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsInitApplication.class, args);
    }

}
