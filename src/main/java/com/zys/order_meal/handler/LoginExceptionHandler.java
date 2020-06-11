package com.zys.order_meal.handler;

import com.zys.order_meal.exception.CustomException;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 返回json数据
@RestControllerAdvice
public class LoginExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResultVo handlerExpiredTokenException(CustomException ex){
        System.out.println(ex.getResultCode());
        return ResultVOUtil.error(1001,"token已过期");
    }
}
