package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
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
    private MenuItemRepository menuItemRepository;

    @Transactional
    @Override
    public OrderItem addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        System.out.println(orderItemDto);
        System.out.println("controller");

        OrderItem request = modelMapper.map(orderItemDto, OrderItem.class);
        request.setOrderItemId(0);
        request.setTotal(orderItemDto.getQuantity() * menuItemRepository.getReferenceById(request.getItemId().getId()).getUnitPrice());


//        System.out.println(request);
        return orderItemService.addToCart(request);
    }

    @Override
    public List<OrderItem> findByOrderUuid(String orderUuid) {
        System.out.println("controller");
        return orderItemService.findByOrderUuid(orderUuid);
    }


    @Override
    public OrderItem updateCart(Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
        if (newQuantity == 0) {
            System.out.println("quantity zero, item removed");
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