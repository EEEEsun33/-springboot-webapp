package com.zys.order_meal.utils;

import java.util.Random;

/**
 * 主键随机数生成
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间 + 随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number) ;
    }

//    生成用户名后的四位数字
    public static synchronized Integer buyerKey(){
        Random random = new Random();
//        生成1000-9999的随机数
        Integer number = random.nextInt(8999) + 1000;

        return number;
    }
}
