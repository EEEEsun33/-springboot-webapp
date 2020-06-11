package com.zys.order_meal.service.impl;

import com.zys.order_meal.dto.OrderDTO;
import com.zys.order_meal.entity.OrderMaster;
import com.zys.order_meal.enums.OrderStatusEnum;
import com.zys.order_meal.enums.PayStatusEnum;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.repository.OrderMasterRepository;
import com.zys.order_meal.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    public OrderDTO goPay(OrderDTO orderDTO){
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(),
                    orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.NEW_PAYED.getCode());
        //TODO
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【支付订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
