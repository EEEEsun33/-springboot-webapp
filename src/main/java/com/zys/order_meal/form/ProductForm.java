package com.zys.order_meal.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品图片
    private String productIcon;

    //商品描述
    private String productDescription;

    //类别编号
    private Integer categoryId;
}
