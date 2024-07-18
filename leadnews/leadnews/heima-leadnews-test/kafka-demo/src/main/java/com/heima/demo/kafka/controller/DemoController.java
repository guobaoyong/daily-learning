package com.heima.demo.kafka.controller;

import com.alibaba.fastjson2.JSON;
import com.heima.demo.kafka.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 高翔宇
 * @since 2024/4/3 周三 下午2:02
 */
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/hello")
    public String hello() {
        kafkaTemplate.send("kafka-hello", "黑马程序员");
        return "ok";
    }

    @GetMapping("/user")
    public String user() {
        kafkaTemplate.send("kafka-user", JSON.toJSONString(User.builder().name("张三").age(18).build()));
        return "ok";
    }
}
