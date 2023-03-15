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

//    @Transactional
//    @Override
//    public String addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        System.out.println(orderItemDto);
//        System.out.println("controller");
////        this will be replaced with logging at info level
//        String verification = "{" + "\"response\" : \"Item added to cart.\"" + "}";
//        OrderItem request = modelMapper.map(orderItemDto, OrderItem.class);
//        orderItemService.addToCart(request);
//        System.out.println(verification);
//        return verification;
//    }

    @Override
    public String addToCart(Integer orderId, Integer menuItemId, Integer quantity) throws InvalidPropertiesFormatException {
        System.out.println("controller");
//        System.out.println(orderId);
//        System.out.println(menuItemId);
//        System.out.println(quantity);
        orderItemService.addToOrder(orderId, menuItemId, quantity);
        return "{" + "\"response\" : \"Item added to order.\"" + "}";
    }

//    @Override
//    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {
//        System.out.println("controller");
//        return orderItemService.findByCartUuid(cartUuid);
//    }

    @Override
    public String updateItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
  return orderItemService.updateOrderItem(orderId, orderItemId, newQuantity);
//        return "{" + "\"response\" : \"Quantity updated.\"" + "}";
    }


//    @Override
//    public OrderItemDto updateCart(Integer orderItemId, Integer newQuantity) {
//        System.out.println("controller");
//        return orderItemService.updateCart(orderItemId, newQuantity);
//    }

//    @Override
//    public void removeCartItem(Integer orderItemId) {
//        System.out.println("controller");
//    orderItemService.removeCartItem(orderItemId);
//    }

}