package org.wlgzs.emails;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.wlgzs.emails.dao")
public class EmailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailsApplication.class, args);
    }
}
