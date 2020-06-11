package com.zys.order_meal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zys.order_meal.enums.ProductStatusEnum;
import com.zys.order_meal.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */
@Entity
@Data
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class ProductInfo {

    //商品id
    @Id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品图片
    private String productIcon;

    //商品描述
    private String productDescription;

    //商品状态( 0正常 , 1下架 )
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    //类别编号
    private Integer categoryId;

//    //创建时间
//    private Date createTime;
//
//    //修改时间
//    private Date updateTime;

    public ProductInfo() { }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus , ProductStatusEnum.class);
    }
}
