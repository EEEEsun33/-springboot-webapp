package com.zys.order_meal.controller;

import com.zys.order_meal.entity.ProductCategory;
import com.zys.order_meal.entity.ProductInfo;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.form.ProductForm;
import com.zys.order_meal.service.CategoryService;
import com.zys.order_meal.service.ProductService;
import com.zys.order_meal.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
 * 卖家端商品Controller层
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 卖家的商品列表
     * @param page  第几页，从第一页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page ,
                             @RequestParam(value = "size" , defaultValue = "5") Integer size ,
                             Map<String , Object> map){
        PageRequest pageRequest = new PageRequest(page - 1 , size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage" , productInfoPage);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("product/list" , map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品上架成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId ,
                                Map<String , Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品下架成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 添加或者修改商品的页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId" , required = false) String productId ,
                              Map<String , Object> map){
        if( !StringUtils.isEmpty(productId)){
            Optional<ProductInfo> productInfo = productService.findById(productId);
            map.put("productInfo" , productInfo.get());
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList" , categoryList);
        return new ModelAndView("product/index" ,map);
    }

    /**
     * 保存/更新 商品
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form ,
                             BindingResult bindingResult ,
                             Map<String , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        try{
            //TODO
            //如果 productId 为空 , 说明是新增商品
            //判断某字符串是否非空，等于 !isEmpty(String str)
            if(StringUtils.isEmpty(form.getProductId())){
                ProductInfo productInfo = new ProductInfo();
                //加密
                form.setProductId(KeyUtil.genUniqueKey());
                //将form拷贝到productInfo
                BeanUtils.copyProperties(form , productInfo);
                productService.save(productInfo);
            }else{
                Optional<ProductInfo> productInfo = productService.findById(form.getProductId());
                BeanUtils.copyProperties(form , productInfo.get());
                productService.save(productInfo.get());
            }
        }catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/seller/product/index");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品操作成功");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 删除商品
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("productId") String productId ,
                               Map<String , Object> map){
        try{
            productService.delete(productId);
        }catch (SellException e){
            map.put("msg" , "商品还在售卖中，不能删除该商品！");
            map.put("url" , "/sell/seller/product/list");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "商品删除成功！");
        map.put("url" , "/sell/seller/product/list");
        return new ModelAndView("common/success" , map);
    }
}

