package com.kafka.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: wangb
 * @time: 2020/12/11 10:21
 */
public class DateUtils {

    public static String getNowDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        return df.format(time);
    }
}
