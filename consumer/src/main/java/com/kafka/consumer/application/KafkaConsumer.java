package com.kafka.consumer.application;

import com.kafka.common.constant.KafkaTopicConstant;
import com.kafka.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author wangb
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {KafkaTopicConstant.TEST_TOPIC_NAME})
    public void listen(ConsumerRecord<?, ?> record) throws Exception {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (!kafkaMessage.isPresent()) {
            throw new Exception("Consumer监听的消息为空值");
        }
        log.info("Consumer接收时间:{}", DateUtils.getNowDate());
        Object message = kafkaMessage.get();
        log.info("topic:{},record={}", KafkaTopicConstant.TEST_TOPIC_NAME, record);
        log.info("topic:{},message={}", KafkaTopicConstant.TEST_TOPIC_NAME, message);

    }
}
