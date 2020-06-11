package com.zys.order_meal.repository;

import com.zys.order_meal.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息的Dao层
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, Integer> {

    /**
     * 通过 phone 查询商家信息
     * @param phone
     * @return
     */
    SellerInfo findByPhone(String phone);

    /**
     * 通过 id 查询商家信息
     * @param sellerId
     * @return
     */
    SellerInfo findBySellerId(Integer sellerId);

}
