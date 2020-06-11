package com.zys.order_meal.service;

import com.zys.order_meal.entity.SellerInfo;

public interface SellerService {

    //通过phone 查询卖家端信息
    SellerInfo findSellerInfoByPhone(String phone);
}
