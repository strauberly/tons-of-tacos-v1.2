package com.adamstraub.tonsoftacos.tonsoftacos.controller;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
//this is where we will write our docs
@RestController
@RequestMapping("/api/order")
public interface OrderItemController {

}

