package com.adamstraub.tonsoftacos.dto.businessDto;

import lombok.Data;

@Data
public class OrderItemReturnedToOwner {
    private Integer orderItemId;
    String itemName;
    private Integer quantity;
    private Double total;

}
