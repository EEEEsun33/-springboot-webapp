package com.zys.order_meal.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 普通java对象（有时被称作POJO）
 * 普通Java对象被称作Entity Bean
 * 用Java Persistence元数据将其映射到数据库
 * 创建一个Entity Bean对象相当于新建一条记录，
 * 删除一个Entity Bean会同时从数据库中删除对应记录，
 * 修改一个Entity Bean时，容器会自动将Entity Bean的状态和数据库同步。
 */

/**
 * 买家信息表
 * @author zys
 * @version 1.0 2020/01/16
 */

@Data
//@Entity说明这个class是实体类，并且使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名
@Entity //实体类中使用@Entity注解，声明了这是一个实体，可以被扫描到。
@DynamicInsert
@DynamicUpdate
public class BuyerInfo {

    /**
     映射规则：

     1. 实体类必须用 @javax.persistence.Entity 进行注解；

     2. 必须使用 @javax.persistence.Id 来注解一个主键；

     3. 实体类必须拥有一个 public 或者 protected 的无参构造函数，之外实体类还可以拥有其他的构造函数；

     4. 实体类必须是一个顶级类（top-level class）。一个枚举（enum）或者一个接口（interface）不能被注解为一个实体；

     5. 实体类不能是 final 类型的，也不能有 final 类型的方法；
     */

    //用户id
    //@Id注解声明了下面的buyerId是主键，
    //@GeneratedValue表示UserID这个对应数据库表中对应字段会自增id，
    //这样我们在前台利用ajax上传新添加的用户信息的时候就不需要我们再去传递id值，
    //因为当我们使用jpa添加记录的时候，id会自动加一作为新纪录的id。
    @Id
    @GeneratedValue
    private Integer buyerId;

    //买家用户名称
    private String username;

    //买家登录密码
    private String password;

    //买家手机
    private String phone;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

}
