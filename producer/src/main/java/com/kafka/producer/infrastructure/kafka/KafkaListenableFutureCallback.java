package com.kafka.producer.infrastructure.kafka;

import com.kafka.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @description:kafka回调函数
 * @author: wangb
 * @time: 2020/12/11 15:16
 */
@Slf4j
public class KafkaListenableFutureCallback implements ListenableFutureCallback<SendResult<String, Object>> {

    @Override
    public void onFailure(Throwable throwable) {
        log.error("async send message fail:{}", throwable.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, Object> result) {
        log.info("async send message success,result={},time:{}", result, DateUtils.getNowDate());

    }
}
