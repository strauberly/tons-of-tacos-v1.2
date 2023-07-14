package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import lombok.Data;

@Data
public class NewOrder {

    private Customer customer;

    private Orders order;

}
