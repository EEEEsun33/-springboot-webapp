package com.zys.order_meal.repository;

import com.zys.order_meal.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 商品类别的Dao层
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     *通过 类别编号 查询类别
     * @param categoryIdList 类别编号的列表
     * @return
     */
//    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);
}
