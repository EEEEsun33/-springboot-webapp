package com.zys.order_meal.entity;

import com.zys.order_meal.enums.OrderStatusEnum;
import com.zys.order_meal.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户订单表
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster {
    //订单的id
    @Id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家的电话
    private String buyerPhone;

    //买家的地址
    private String buyerAddress;

    //买家id
    private Integer buyerId;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态，默认为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态,默认为0,未支付
    //TODO
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
