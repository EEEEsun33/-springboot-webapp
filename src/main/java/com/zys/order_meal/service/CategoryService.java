package com.zys.order_meal.service;

import com.zys.order_meal.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * 商品类别的Service层接口
 */

public interface CategoryService {
    //根据类别的id查找类别的信息
    Optional<ProductCategory> findById(Integer categoryId);

    //查找所有的类别信息
    List<ProductCategory> findAll();

    //根据类别编号列表的值，查找出类别信息（多条）
//    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList);

    //保存类别信息
    ProductCategory save(ProductCategory productCategory);

    //删除类别
    void delete(Integer categoryId);
}
