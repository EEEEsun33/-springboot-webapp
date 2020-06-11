package com.zys.order_meal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5Buyer {
    private String buyerName;
    private BigDecimal sum;

}
