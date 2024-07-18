package com.heima.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author 高翔宇
 * @since 2024/4/2 周二 下午3:46
 */
public class ConsumerQuickStart {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.110.133:9092");
        // 消费者组，多个消费者订阅同一个主题，只有一个消费者能消费到消息
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // 创建消费者
        /*try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singleton("topic-first"));
            while (true) {
                consumer.poll(Duration.ofMillis(1000)).forEach(record -> {
                    System.out.printf("key = %s, value = %s, offset（偏移量） = %d, partition（分区） = %d%n",
                            record.key(), record.value(), record.offset(), record.partition());
                    // 手动同步提交偏移量
//                    consumer.commitSync();
                });
                // 手动异步提交偏移量
                consumer.commitAsync((offsets, e) -> {
                    if (e != null) {
                        System.out.println("提交失败:" + e.getMessage());
                        System.out.printf("offsets（偏移量） = %s%n", offsets);
                    } else {
                        System.out.println("提交成功");
                    }
                });
            }
        }*/

        // 异步提交和同步提交结合使用
        KafkaConsumer<String, String> consumer = null;
        try {
            consumer = new KafkaConsumer<>(properties);
            consumer.subscribe(Collections.singleton("topic-first"));
            while (true) {
                KafkaConsumer<String, String> finalConsumer = consumer;
                consumer.poll(Duration.ofMillis(1000)).forEach(record -> {
                    System.out.printf("key = %s, value = %s, offset（偏移量） = %d, partition（分区） = %d%n",
                            record.key(), record.value(), record.offset(), record.partition());
                    // 手动同步提交偏移量
                    finalConsumer.commitAsync();
                });
            }
        } finally {
            if (consumer != null) {
                consumer.commitSync();
            }
        }
    }
}
