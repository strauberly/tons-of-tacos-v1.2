package com.adamstraub.tonsoftacos.tonsoftacos.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Integer itemId;
    private String cartUuid;
    private Integer quantity;
    private Double total;
}
