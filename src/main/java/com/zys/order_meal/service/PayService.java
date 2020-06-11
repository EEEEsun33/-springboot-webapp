package com.zys.order_meal.service;

import com.zys.order_meal.dto.OrderDTO;

public interface PayService {

    public OrderDTO goPay(OrderDTO orderDTO);

    //退款
//    public RefundResponse refund(OrderDTO orderDTO);
}
