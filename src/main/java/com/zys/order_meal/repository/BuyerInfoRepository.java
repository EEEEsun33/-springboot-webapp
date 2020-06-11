package com.zys.order_meal.repository;

import com.zys.order_meal.entity.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 买家信息Dao接口
 */

/**
 * JPARepository: 继承了PagingAndSortingRepository接口 -> 继承了CrudRepository -> 继承了Repository
 * 对继承父接口中方法的返回值进行了适配,因为在父类接口中通常都返回迭代器，需要我们自己进行强制类型转化。
 * 而在JpaRepository中，直接返回了List
 * 方法的名称要遵循 findBy + 属性名（首字母大写） + 查询条件(首字母大写 Is Equals)
 */

/**
 * JpaSpecificationExecutor
 * 主要提供了多条件查询的支持，并且可以在查询中添加分页和排序。
 * 是单独存在的，需要配合这JpaRepository一起使用
 */
@Repository
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo, Integer>, JpaSpecificationExecutor<BuyerInfo> {
    /**
     * 通过 phone 查询用户信息
     * @param phone
     * @return
     */
    BuyerInfo findByPhone(String phone);

    /**
     * 通过 id 查询用户信息
     * @param buyerId
     * @return
     */
    BuyerInfo findByBuyerId(Integer buyerId);

}
