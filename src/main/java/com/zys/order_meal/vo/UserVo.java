package com.zys.order_meal.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zys.order_meal.entity.BuyerInfo;
import lombok.Data;

@Data
public class UserVo {
    @JsonProperty("token")
    private String token;

    @JsonProperty("buyerInfo")
    private BuyerInfo buyerInfo;
}
