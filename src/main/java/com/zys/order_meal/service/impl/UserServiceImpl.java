package com.zys.order_meal.service.impl;

import com.zys.order_meal.entity.BuyerInfo;
import com.zys.order_meal.entity.SellerInfo;
import com.zys.order_meal.repository.BuyerInfoRepository;
import com.zys.order_meal.service.UserService;
import com.zys.order_meal.utils.KeyUtil;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import com.zys.order_meal.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Override
    public Boolean BuyerRegister (BuyerInfo buyerInfo) {
        if (buyerInfoRepository.findByPhone(buyerInfo.getPhone()) == null) {
            buyerInfoRepository.save (buyerInfo);
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public Boolean BuyerLogin(String phone, String password) {
        BuyerInfo buyerInfo  = buyerInfoRepository.findByPhone(phone);

        if (buyerInfo.getPhone().equals(phone) && buyerInfo.getPassword().equals(password)){
            return true;
        }
        else {
            return false;
        }
    }

//    @Override
//    public Boolean SellerRegister(SellerInfo sellerInfo) {
//        return null;
//    }
//
//    @Override
//    public Boolean SellerLogin(String phone, String password) {
//        return null;
//    }
}
