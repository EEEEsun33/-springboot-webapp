package com.zys.order_meal.service;

import com.zys.order_meal.entity.BuyerInfo;
import com.zys.order_meal.entity.SellerInfo;
import com.zys.order_meal.vo.ResultVo;

/**
 * 用户登录注册的接口
 */
public interface UserService {
    Boolean BuyerRegister (BuyerInfo buyerInfo);

    Boolean BuyerLogin(String phone,String password);


//    Boolean SellerRegister (SellerInfo sellerInfo);
//
//    Boolean SellerLogin(String phone,String password);
}
