package com.yao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    /**
     * datetime类型转字符串
     * @return
     */
    public static String dateToString(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//hh小时12小时制
        return format.format(date);
    }
}
