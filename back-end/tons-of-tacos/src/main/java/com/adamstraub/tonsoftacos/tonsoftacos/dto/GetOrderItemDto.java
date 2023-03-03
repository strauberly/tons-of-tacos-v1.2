package com.adamstraub.tonsoftacos.tonsoftacos.dto;
import lombok.Data;
import lombok.Value;

@Data
public class GetOrderItemDto {
    String itemName;
    Double unitPrice;
     Integer quantity;
     Double total;
}
