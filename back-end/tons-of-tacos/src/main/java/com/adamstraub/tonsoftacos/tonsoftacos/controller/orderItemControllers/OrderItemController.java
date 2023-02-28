package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


@RestController
public class OrderItemController implements OrderItemControllerInterface {
@Autowired
    private ModelMapper modelMapper;
@Autowired
    private OrderItemService orderItemService;

    @Override
    public OrderItem addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        request in as dto then convert to orderItem then send to service


        System.out.println(orderItemDto);
        System.out.println("controller");

        OrderItem request = modelMapper.map(orderItemDto, OrderItem.class);
        request.setOrderItemId(0);
        System.out.println(request);

//        OrderItem orderItem = orderItemService.addToCart(request);
////        System.out.println(orderItem);


//        OrderItemDto response = modelMapper.map(orderItem, OrderItemDto.class);
//
//        System.out.println("response is" + response);
//
//        ResponseEntity<OrderItemDto> newResponse = new ResponseEntity<>(response, HttpStatus.CREATED);
//        System.out.println(newResponse);
        return orderItemService.addToCart(request);
    }




//    @Override
//    public ResponseEntity<OrderItemDto> addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        System.out.println(orderItemDto);
//        System.out.println("controller");
//
//        OrderItem request = modelMapper.map(orderItemDto, OrderItem.class);
//        request.setOrderItemId(0);
////        System.out.println(request);
//
//        OrderItem orderItem = orderItemService.addToCart(request);
////        System.out.println(orderItem);
//
//
//        OrderItemDto response = modelMapper.map(orderItem, OrderItemDto.class);
//
//        System.out.println("response is" + response);
//
//        ResponseEntity<OrderItemDto> newResponse = new ResponseEntity<>(response, HttpStatus.CREATED);
//        System.out.println(newResponse);
//        return newResponse;
//    }
//




//

    @Override
    public List<OrderItem> findByOrderUuid(String orderUuid) {
        System.out.println("controller");
        return orderItemService.findByOrderUuid(orderUuid);
    }
//
    @Override
    public OrderItem updateCart(Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
        if (newQuantity == 0) {
            System.out.println("quantity zero id removed");
            return orderItemService.removeCartItem(orderItemId);
        } else {
            return orderItemService.updateCart(orderItemId, newQuantity);
        }
    }
//
    @Override
    public void removeCartItem(Integer orderItemId) {
        System.out.println("controller");
    orderItemService.removeCartItem(orderItemId);
    }

}