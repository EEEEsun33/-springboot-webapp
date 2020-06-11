package com.zys.order_meal.repository;

import com.zys.order_meal.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 商品信息的Dao层
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 通过 productStatus 查询所有的商品信息
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 通过 productCategory 查询所有的商品信息
     * @param productCategory
     * @return
     */
    List<ProductInfo> findByCategoryId(Integer productCategory);

}
