package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import lombok.Data;

import java.util.List;

@Data
public class NewOrderDto {

    private Customer customer;

    private Orders order;

//    customer name email and phone
//    private String name;
//
//    private String email;
//
//    private String phoneNumber;
//
////    order uid orderitems total
//
//    private Double orderTotal;
//
//    private String orderUid;
//
//    private List<OrderItem> orderItems;

}
