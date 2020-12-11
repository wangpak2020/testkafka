package com.kafka.producer.interfaces.facade;

import com.kafka.common.dto.base.Response;
import com.kafka.producer.application.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/producer")
public class MessageController {
    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping(value = "/send")
    public Response send(String msg) {
        try {
            kafkaProducer.send(msg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.newResponse().error(e);
        }
        return Response.newResponse().OK();
    }

}
