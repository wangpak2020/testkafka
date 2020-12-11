package com.kafka.producer.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kafka.common.constant.KafkaTopicConstant;
import com.kafka.common.dto.Message;
import com.kafka.common.util.DateUtils;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    AtomicLong atomicLong = new AtomicLong(0L);

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("中国崛起:" + atomicLong.incrementAndGet());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++send  message = {}", gson.toJson(message));
        String localTime = DateUtils.getNowDate();
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(KafkaTopicConstant.TEST_TOPIC_NAME, message.getId().toString(), gson.toJson(message));
        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error(KafkaTopicConstant.TEST_TOPIC_NAME + "-生产者发送消息失败" + throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, String> stringObjectSendResult) {
                log.info(KafkaTopicConstant.TEST_TOPIC_NAME + "-生产者发送消息成功," + stringObjectSendResult.toString() + "时间:" + localTime);
            }
        });

    }


    public void send(final String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++send  message = {}", gson.toJson(message));
        String localTime = DateUtils.getNowDate();
        ListenableFuture<SendResult<String, String>> resp = kafkaTemplate.send(KafkaTopicConstant.TEST_TOPIC_NAME, message.getId().toString(), gson.toJson(message));
        resp.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error(KafkaTopicConstant.TEST_TOPIC_NAME + "-生产者发送消息失败" + throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, String> stringObjectSendResult) {
                log.info(KafkaTopicConstant.TEST_TOPIC_NAME + "-生产者发送消息成功," + stringObjectSendResult.toString() + "时间:" + localTime);
            }
        });

    }
}

