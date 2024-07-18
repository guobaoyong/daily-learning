package com.heima.demo.kafka.listener;

import com.alibaba.fastjson2.JSON;
import com.heima.demo.kafka.domain.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 高翔宇
 * @since 2024/4/3 周三 下午2:09
 */
@Component
public class HelloListener {
    @KafkaListener(topics = "kafka-hello")
    public void onMessage(String message) {
        if (StringUtils.hasLength(message)) {
            System.out.println("接收到消息：" + message);
        }
    }

    @KafkaListener(topics = "kafka-user")
    public void onUser(String user) {
        if (user != null) {
            System.out.println("接收到用户消息：" + JSON.parseObject(user, User.class));
        }
    }
}
