package com.hoangtm14.spring.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return toDate(localDateTime);
    }
}
