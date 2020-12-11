package com.kafka.common.dto.base;

/**
 * @Classname ResponseEnum
 * @Description 返回状态枚举
 * @Date 2020/6/2 17:00
 * @Created by gangye
 */
public enum ResponseEnum {
    SUCCESS(200,"ok"),
    SERVER_ERROR(500,"服务器错误");

    private int code;

    private String message;

    ResponseEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
