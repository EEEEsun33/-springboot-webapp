package com.zys.order_meal.exception;

import com.zys.order_meal.enums.ResultEnum;

import java.text.MessageFormat;

public class CustomException extends RuntimeException {
    //错误代码
    ResultEnum resultCode;

    public CustomException(ResultEnum resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public CustomException(ResultEnum resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultEnum getResultCode(){
        return resultCode;
    }
}
