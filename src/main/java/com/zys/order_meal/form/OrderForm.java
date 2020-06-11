package com.zys.order_meal.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 订单表单验证
 */
@Data
public class OrderForm {

    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;

    //买家电话
    @NotEmpty(message = "手机号必填")
    private String phone;

    //买家地址
    @NotEmpty(message = "地址必填")
    private String address;

    //买家id
    @NotNull(message = "id不能为空")
    private Integer buyid;

    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
