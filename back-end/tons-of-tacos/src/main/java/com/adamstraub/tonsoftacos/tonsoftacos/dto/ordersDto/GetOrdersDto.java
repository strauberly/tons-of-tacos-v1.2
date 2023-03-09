package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
public class GetOrdersDto {
    private Integer customerId;
    private Double orderTotal;
    private String orderUid;
    private Timestamp created;
    private List<GetOrderItemDto> orderItems;



}
