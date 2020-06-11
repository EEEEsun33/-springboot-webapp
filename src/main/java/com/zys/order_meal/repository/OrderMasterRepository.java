package com.zys.order_meal.repository;

import com.zys.order_meal.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * 用户订单表Dao层
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    /**
     * 通过buyerId查询所有订单
     * @param buyerId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerId(Integer buyerId , Pageable pageable);

    /**
     * 通过buyerId 和 orderStatus 查询订单
     * @param buyerId
     * @param orderStatus
     * @return
     */
    List<OrderMaster> findByBuyerIdAndOrderStatus(Integer buyerId, Integer orderStatus);

    /**
     * 根据时间分页查询订单
     * @param pageable
     * @return
     */
    @Query("select o from OrderMaster o ORDER BY o.createTime DESC ")
    Page<OrderMaster> findOrderMastersByCreateTimeDesc(Pageable pageable);

    /**
     * 查询每月消费金额前五的顾客
     * @return
     */
    @Query("select buyerName as name,sum(orderAmount) as total from OrderMaster where date_format(createTime,'%Y-%m')=date_format(now(),'%Y-%m') group by buyerName")
    List<Object[]> queryTop5();

}
