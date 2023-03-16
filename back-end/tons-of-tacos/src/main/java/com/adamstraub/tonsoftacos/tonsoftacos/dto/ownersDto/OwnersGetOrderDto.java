package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OwnersGetOrderDto {
    private Integer orderId;
    private String name;
    private String email;
    private String phone;
    private String orderUid;
    private List<OwnersOrderItemDto> orderItems;
    private Double orderTotal;
    private Timestamp created;
    private String ready;
    private String status;
}
