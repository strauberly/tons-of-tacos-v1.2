package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderItemController implements OrderItemControllerInterface {
@Autowired
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem addToCart(OrderItem orderItem) {
        return orderItemService.addToCart(orderItem);
    }
    @Override
    public List<OrderItem> findByOrderUuid(String  orderUuid) {
        return orderItemService.findByUuid(orderUuid);
    }
}