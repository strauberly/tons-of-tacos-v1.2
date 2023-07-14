package com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto;

import lombok.Data;

@Data
public class BusinessOrderItem {
    private Integer orderItemId;
    String itemName;
    private Integer quantity;
    private Double total;

}
