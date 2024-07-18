package com.heima.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author 高翔宇
 * @since 2024/4/2 周二 下午3:47
 */
public class ProducerQuickStart {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.110.133:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 确认机制
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 压缩算法
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>("topic-first", "key-001", "hello kafka");
            // 同步发送
//            RecordMetadata recordMetadata = producer.send(record).get();
//            System.out.printf("offset（偏移量） = %d%n", recordMetadata.offset());

            // 异步发送
            producer.send(record, (metadata, e) -> {
                if (e != null) {
                    System.out.println("发送失败:" + e.getMessage());
                } else {
                    System.out.printf("offset（偏移量） = %d%n", metadata.offset());
                }
            });
        }
    }
}
