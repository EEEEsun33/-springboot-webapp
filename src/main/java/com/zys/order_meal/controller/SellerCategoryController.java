package com.zys.order_meal.controller;

import com.zys.order_meal.entity.ProductCategory;
import com.zys.order_meal.entity.ProductInfo;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.form.CategoryForm;
import com.zys.order_meal.service.CategoryService;
import com.zys.order_meal.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 商品类别Controller层
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    /**
     * 类别列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String , Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList" , productCategoryList);
        return new ModelAndView("category/list" , map);
    }

    /**
     * 修改 / 新增页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId" , required = false) Integer categoryId ,
                              Map<String , Object> map){
        if(categoryId != null){
            Optional<ProductCategory> productCategory = categoryService.findById(categoryId);
            map.put("productCategory" , productCategory.get());
        }
        return new ModelAndView("category/index" , map);
    }

    /**
     * 新增 / 修改后 , 保存类别
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult ,
                             Map<String  , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("common/error" , map);
        }
        try {
            //修改
            if(categoryForm.getCategoryId() != null){
                //查询类别
                Optional<ProductCategory> productCategory = categoryService.findById(categoryForm.getCategoryId());
                //查询对应类别下的商品信息
                List<ProductInfo> productInfoList = productService.findByCategoryId(productCategory.get().getCategoryId());
                BeanUtils.copyProperties(categoryForm , productCategory.get());
                //保存编辑后的商品信息
                categoryService.save(productCategory.get());
                //遍历修改商品的类别
                for(ProductInfo productInfo : productInfoList){
                    productInfo.setCategoryId(productCategory.get().getCategoryId());
                    productService.save(productInfo);
                }
            }else{
                //新建
                ProductCategory productCategory = new ProductCategory();
                BeanUtils.copyProperties(categoryForm , productCategory);
                categoryService.save(productCategory);
            }
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/category/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类别操作成功！");
        map.put("url" , "/sell/seller/category/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 删除商品类被
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("categoryId") Integer categoryId ,
                               Map<String , Object> map){
        try {
            categoryService.delete(categoryId);
        }catch (SellException e){
            map.put("msg" , "还有商品属于该类别 , 不能删除该类别！");
            map.put("url" , "/sell/seller/category/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "类别删除成功！");
        map.put("url" , "/sell/seller/category/list");
        return new ModelAndView("common/success" , map);
    }
}
