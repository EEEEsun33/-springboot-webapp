package com.zys.order_meal.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 公告表单对象
 */
@Data
public class NoticeForm {
    //公告id
    @NotEmpty(message = "公告id必填")
    private Integer noticeId;

    //公告标题
    @NotEmpty(message = "公告标题必填")
    private String noticeTitle;

    //公告内容
    @NotEmpty(message = "公告内容必填")
    private String noticeContent;
}
