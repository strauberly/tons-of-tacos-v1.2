package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Order {
private Integer orderId;
private Integer customerId;
private Timestamp created;
private Double orderTotal;
private String orderUuid;

}
