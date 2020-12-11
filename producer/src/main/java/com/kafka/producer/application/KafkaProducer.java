package com.kafka.producer.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kafka.common.constant.KafkaTopicConstant;
import com.kafka.common.dto.Message;
import com.kafka.producer.infrastructure.kafka.KafkaListenableFutureCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    AtomicLong atomicLong = new AtomicLong(0L);

    /**
     * 异步发送
     */
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("msg:" + atomicLong.incrementAndGet());
        message.setSendTime(new Date());
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(KafkaTopicConstant.TEST_TOPIC_NAME, message.getId().toString(), gson.toJson(message));
        send.addCallback(new KafkaListenableFutureCallback());
    }


    public void send(String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(KafkaTopicConstant.TEST_TOPIC_NAME, message.getId().toString(), gson.toJson(message));
        send.addCallback(new KafkaListenableFutureCallback());
    }
}

