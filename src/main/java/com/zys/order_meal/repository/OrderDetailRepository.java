package com.zys.order_meal.repository;

import com.zys.order_meal.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 订单详情Dao层
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过 orderId 查询所有的订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

    /**
     * 查询每月商品的销售量
     * @return
     */
    @Query("select productName,count(*) as icount from OrderDetail where date_format(createTime,'%Y-%m') = date_format(now(),'%Y-%m') group by productName order by icount")
    List<Object[]> queryByMonthlySales();
}
