package com.kafka.consumer.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务监控检查
 *
 * @author wangb
 * @since 2020-06-09
 */
@Slf4j
@RestController
public class HealthCheckController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.profiles.active}")
    private String profilesActive;

    @GetMapping(value = "ping")
    @ResponseBody
    public Map<String, Object> healthCheck() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "pong");
        map.put("applicationName", applicationName);
        map.put("serverPort", serverPort);
        map.put("profilesActive", profilesActive);
        return map;
    }


}

