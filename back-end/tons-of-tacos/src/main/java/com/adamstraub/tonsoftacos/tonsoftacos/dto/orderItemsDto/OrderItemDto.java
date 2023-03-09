package com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Integer itemId;
    private Integer quantity;
    private Double total;
}
