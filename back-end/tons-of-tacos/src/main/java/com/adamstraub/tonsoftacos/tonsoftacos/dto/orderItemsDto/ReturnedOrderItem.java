package com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto;
import lombok.Data;

@Data
public class ReturnedOrderItem {
    String itemName;
    Double unitPrice;
     Integer quantity;
     Double total;
}
