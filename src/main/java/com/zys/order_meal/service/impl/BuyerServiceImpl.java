package com.zys.order_meal.service.impl;

import com.zys.order_meal.dto.OrderDTO;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.service.BuyerService;
import com.zys.order_meal.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    //判断是否属于自己的订单方法
    private OrderDTO checkOrderOwner(Integer buyerId , String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        //判断订单是否为空
        if(orderDTO == null){
            return null;
        }
        //判断是否属于自己的订单
//        if( !orderDTO.getBuyerOpenid().equalsIgnoreCase(buyerId)){
        if( orderDTO.getBuyerId() != buyerId){
            log.error("【查询订单】订单的buyerId不一致 , buyerId={} , orderDTO={}" ,buyerId , orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO findOrderOne(Integer buyerId, String orderId) {
        return checkOrderOwner(buyerId , orderId);
    }

    @Override
    public List<OrderDTO> findOrderList(Integer buyerId, Integer status) {
        List<OrderDTO> list = new ArrayList<>();
        list.clear();

        List<OrderDTO> listStats = orderService.findListStats(buyerId , status);
        listStats.forEach((orderDTO) -> {
            String orderId = orderDTO.getOrderId();
            OrderDTO one = orderService.findOne(orderId);
            list.add(one);
        });
        return list;
    }

    @Override
    public OrderDTO cancelOrder(Integer buyerId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(buyerId, orderId);
        if(orderDTO == null){
            log.error("【取消订单】查不到改订单 , orderId={}" , orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }
}
