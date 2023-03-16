package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import lombok.Data;

@Data
public class OwnersOrderItemDto {
    private Integer orderItemId;
    String itemName;
    private Integer quantity;
    private Double total;

}
