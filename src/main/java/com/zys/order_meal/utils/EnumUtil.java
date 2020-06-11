package com.zys.order_meal.utils;

import com.zys.order_meal.enums.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code , Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
