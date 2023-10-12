package com.adamstraub.tonsoftacos.dto.customerDto.orderItemsDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemReturnedToCustomer {
    String itemName;
//    Double unitPrice;
     BigDecimal unitPrice;
     Integer quantity;
//     Double total;
     BigDecimal total;

}
