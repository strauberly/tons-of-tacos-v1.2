package com.adamstraub.tonsoftacos.dto.businessDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemReturnedToOwner {
    private Integer orderItemId;
    String itemName;
    private Integer quantity;
//    private Double total;
    private BigDecimal total;

}
