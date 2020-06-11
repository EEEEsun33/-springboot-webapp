package com.zys.order_meal.controller;

import com.zys.order_meal.entity.ProductCategory;
import com.zys.order_meal.entity.ProductInfo;
import com.zys.order_meal.service.CategoryService;
import com.zys.order_meal.service.ProductService;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ProductInfoVo;
import com.zys.order_meal.vo.ProductVo;
import com.zys.order_meal.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品列表
     * @return
     */
    @GetMapping("/list")
    public ResultVo list(){

        //查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类别
        //java8的lambda方法
        // stream():把集合转换成流
        // map():用于映射每个元素到对应的结果。
        // Collectors(): 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        //把商品对应的类别id映射出来
        List<Integer> categoryIdList = productInfoList.stream().map(e -> e.getCategoryId ()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryIdIn(categoryIdList);

        //数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryId());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryId().equals(productCategory.getCategoryId())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo , productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVOUtil.success(productVoList);
    }
}
