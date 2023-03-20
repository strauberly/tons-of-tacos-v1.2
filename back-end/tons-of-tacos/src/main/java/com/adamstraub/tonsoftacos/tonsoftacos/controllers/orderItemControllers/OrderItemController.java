package com.adamstraub.tonsoftacos.tonsoftacos.controllers.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


@RestController
public class OrderItemController implements OrderItemControllerInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;


    @Override
    public String addToCart(Integer orderId, Integer menuItemId, Integer quantity) throws InvalidPropertiesFormatException {
        System.out.println("controller");
        orderItemService.addToOrder(orderId, menuItemId, quantity);
//        move all the way to service or just as log
        return "{" + "\"response\" : \"Item added to order.\"" + "}";
    }



    @Override
    public String updateItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
  return orderItemService.updateOrderItem(orderId, orderItemId, newQuantity);
    }
}