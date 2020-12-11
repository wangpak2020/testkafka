package com.kafka.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: wangb
 * @time: 2020/12/11 10:09
 */
@Data
public class Message {

    private Long id;
    /**
     * 消息
     */
    private String msg;
    /**
     * 时间戳
     */
    private Date sendTime;

}
