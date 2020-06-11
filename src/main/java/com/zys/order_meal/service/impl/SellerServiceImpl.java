package com.zys.order_meal.service.impl;

import com.zys.order_meal.entity.SellerInfo;
import com.zys.order_meal.repository.SellerInfoRepository;
import com.zys.order_meal.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByPhone(String phone) {
        return repository.findByPhone(phone);
    }

}
