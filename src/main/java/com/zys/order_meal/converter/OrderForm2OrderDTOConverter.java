package com.zys.order_meal.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zys.order_meal.dto.OrderDTO;
import com.zys.order_meal.entity.OrderDetail;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderForm 对象转换成 OrderDTO 对象
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO conver(OrderForm orderForm){
//        GSON是Google提供的用来在Java对象和JSON数据之间进行映射的Java类库。
//        可以将一个Json字符转成一个Java对象，或者将一个Java转化为Json字符串。
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerId(orderForm.getBuyid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // fromJson()方法来实现从Json相关对象到Java实体的方法。
            orderDetailList = gson.fromJson(orderForm.getItems() ,
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】 错误 , string={}" , orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
