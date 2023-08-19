package com.adamstraub.tonsoftacos.dto.customerDto.orderItemsDto;
import lombok.Data;

@Data
public class OrderItemReturnedToCustomer {
    String itemName;
    Double unitPrice;
     Integer quantity;
     Double total;
}
