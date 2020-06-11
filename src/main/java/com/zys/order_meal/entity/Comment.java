package com.zys.order_meal.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 评论表
 * @author zys
 * @version 1.0 2020/02/18
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Comment {

    //评论表id
    @Id
    @GeneratedValue
    private Integer commentId;

    //评论者的名字
    private String commentName;

    //评论者的buyerid
    private Integer buyerId;

    //评论内容
    private String commentContent;

    //创建时间
    private Date createTime;

    //评论的订单
    private String orderId;

}
