package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.NoSuchElementException;


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

//    entity
//    @Override
//    public OrderItem addToCart(OrderItem orderItem) throws InvalidPropertiesFormatException {
//                orderItem.setOrderItemId(0);
//        orderItem.setTotal(orderItem.getQuantity() * menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
//        return orderItemService.addToCart(orderItem);
//    }

    //    dto
    @Transactional
    @Override
    public String addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        System.out.println(orderItemDto);
        System.out.println("controller");
        String verification = "{" + "\"response\" : \"Item added to cart.\"" + "}";
        OrderItem request = modelMapper.map(orderItemDto, OrderItem.class);
        orderItemService.addToCart(request);
        System.out.println(verification);
        return verification;
    }

    @Override
    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {
        System.out.println("controller");
        return orderItemService.findByCartUuid(cartUuid);
    }

    @Override
    public OrderItemDto updateCart(Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
        return orderItemService.updateCart(orderItemId, newQuantity);
    }
//    @Override
//    public GetOrderItemDto updateCart(Integer orderItemId, Integer newQuantity) {
//        System.out.println("controller");
//        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
//        if (orderItem.getOrderItemId() == null) {
//            throw new NoSuchElementException("That order id does not exist and cannot be updated.");
//        }
//        if (newQuantity == 0) {
//            return orderItemService.removeCartItem(orderItemId);
//        } else {
//            return orderItemService.updateCart(orderItemId, newQuantity);
//        }
//    }
//
    @Override
    public void removeCartItem(Integer orderItemId) {
        System.out.println("controller");
    orderItemService.removeCartItem(orderItemId);
    }

}