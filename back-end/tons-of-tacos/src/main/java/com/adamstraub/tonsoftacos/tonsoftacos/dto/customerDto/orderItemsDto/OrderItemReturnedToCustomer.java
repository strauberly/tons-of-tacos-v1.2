package com.adamstraub.tonsoftacos.tonsoftacos.dto.customerDto.orderItemsDto;
import lombok.Data;

@Data
public class OrderItemReturnedToCustomer {
    String itemName;
    Double unitPrice;
     Integer quantity;
     Double total;
}
