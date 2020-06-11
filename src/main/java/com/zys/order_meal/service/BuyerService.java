package com.zys.order_meal.service;

import com.zys.order_meal.dto.OrderDTO;

import java.util.List;

/**
 * 买家Service层接口
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(Integer buyerId , String orderId);

    //查询某个用户的所有订单
    List<OrderDTO> findOrderList(Integer buyerId , Integer status);

    //取消订单
    OrderDTO cancelOrder(Integer buyerId , String orderId);
}
