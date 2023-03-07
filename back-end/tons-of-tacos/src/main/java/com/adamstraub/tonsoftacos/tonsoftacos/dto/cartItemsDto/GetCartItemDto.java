package com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto;
import lombok.Data;

@Data
public class GetCartItemDto {
    String itemName;
    Double unitPrice;
     Integer quantity;
     Double total;
}
