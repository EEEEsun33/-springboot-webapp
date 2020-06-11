package com.zys.order_meal.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Notice {
    //公告id
    @Id
    @GeneratedValue
    private Integer noticeId;

    private String noticeTitle;

    //公告内容
    private String noticeContent;

    //公告编辑者
    private Integer noticeEditor;

    //创建时间
    private Date createTime;
}
