package qqzsh.top.search.sender.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import qqzsh.top.search.mysql.dto.MySqlRowData;
import qqzsh.top.search.sender.ISender;

import java.util.Optional;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:26
 * @Description
 */
@Component("kafkaSender")
public class KafkaSender implements ISender {

    @Value("${adconf.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sender(MySqlRowData rowData) {

        kafkaTemplate.send(
                topic, JSON.toJSONString(rowData)
        );
    }

    @KafkaListener(topics = {"search-mysql-data"}, groupId = "search")
    public void processMysqlRowData(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            MySqlRowData rowData = JSON.parseObject(
                    message.toString(),
                    MySqlRowData.class
            );
            System.out.println("kafka processMysqlRowData: " +
                    JSON.toJSONString(rowData));
        }
    }
}

