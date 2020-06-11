package com.zys.order_meal.aspect;
import com.zys.order_meal.constant.CookieConstant;
import com.zys.order_meal.exception.SellerAuthorizeException;
import com.zys.order_meal.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家登录的AOP
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Pointcut("execution(public * com.zys.order_meal.controller.Seller*.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request , CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】 Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }

    }
}
