package com.kafka.consumer.application;

import com.kafka.common.constant.KafkaTopicConstant;
import com.kafka.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author vincent.li
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = {KafkaTopicConstant.TEST_TOPIC_NAME})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        log.info("接收时间:{}", DateUtils.getNowDate());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info(KafkaTopicConstant.TEST_TOPIC_NAME + "----------------- record==>{}", record);
            log.info(KafkaTopicConstant.TEST_TOPIC_NAME + "------------------ message==>{}", message);
        }

    }
}
