package com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto;

import lombok.Data;

@Data
public class CartItemDto {
    private Integer itemId;
    private String cartUuid;
    private Integer quantity;
    private Double total;
}
