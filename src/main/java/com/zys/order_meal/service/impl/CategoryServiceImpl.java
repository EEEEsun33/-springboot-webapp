package com.zys.order_meal.service.impl;

import com.zys.order_meal.entity.ProductCategory;
import com.zys.order_meal.entity.ProductInfo;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.repository.ProductCategoryRepository;
import com.zys.order_meal.repository.ProductInfoRepository;
import com.zys.order_meal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 商品类别的Service层实现类
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    //根据类别的id查找类别的信息
    @Override
    public Optional<ProductCategory> findById(Integer categoryId) {
        return repository.findById(categoryId);
    }

    //查找所有的类别信息
    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    //根据类别编号列表的值，查找出类别信息（多条）
    @Override
    public List<ProductCategory> findByCategoryIdIn(List<Integer> categoryIdList) {
        return repository.findByCategoryIdIn(categoryIdList);
    }

    //保存类别信息
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    //删除类别
    @Override
    public void delete(Integer categoryId) {
        Optional<ProductCategory> category = repository.findById(categoryId);
//        List<ProductInfo> productInfoList = productInfoRepository.findByCategoryType(category.get().getCategoryType());
        List<ProductInfo> productInfoList = productInfoRepository.findByCategoryId(category.get().getCategoryId ());
        if( !productInfoList.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_IN_CATEGORY_ERROR);
        }
        repository.delete(category.get());
    }

}
