package com.zys.order_meal.controller;

import com.zys.order_meal.constant.CookieConstant;
import com.zys.order_meal.entity.SellerInfo;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.repository.SellerInfoRepository;
import com.zys.order_meal.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * 卖家操作的Controller层
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 卖家登录
     * @param phone
     * @param password
     * @param response
     * @return
     */
    @GetMapping("/login")
    public String loginAdmin(@RequestParam("phone") String phone ,
                             @RequestParam("password") String password ,
                             HttpServletResponse response){
        SellerInfo sellerInfo = sellerInfoRepository.findByPhone(phone);
        log.info("【商家登录】 商家信息={}" , sellerInfo);
        if(sellerInfo != null && sellerInfo.getPassword().equals(password)){
            //生成token
            String token = UUID.randomUUID().toString();
            log.info("【商家登录】 登录成功的token = {}" , token);
            //设置过期时间
            Integer expire = 7200;
            //设置token至cookie
            CookieUtil.set(response , CookieConstant.TOKEN , token , expire);
            return "登录成功";
        }else{
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
    }

    /**
     * 卖家注册
     * @param phone
     * @param password
     * @param response
     * @return
     */
    @PostMapping("/register")
    public String registerAdmin(@RequestParam("phone") String phone ,
                                @RequestParam("password") String password ,
                                HttpServletResponse response){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setPhone(phone);
        sellerInfo.setPassword(password);
        sellerInfoRepository.save(sellerInfo);
        log.info("【商家登录】 商家信息={}" , sellerInfo);
        return "注册成功";
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request ,
                               HttpServletResponse response ,
                               Map<String , Object> map){
        //从cookie里查询
        Cookie cookie = CookieUtil.get(request , CookieConstant.TOKEN);
        if(cookie != null){
            //清除cookie
            CookieUtil.set(response , CookieConstant.TOKEN , null , 0);
        }
        map.put("msg" , ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url" , "/sell/seller/order/list");
        return new ModelAndView("common/success" , map);
    }
}
