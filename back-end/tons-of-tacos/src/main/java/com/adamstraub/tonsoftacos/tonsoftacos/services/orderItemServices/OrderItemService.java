package com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class OrderItemService implements OrderItemServiceInterface {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    @Transactional
    public String updateOrderItem(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("service");
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        System.out.println(orderItem);
        Orders order = ordersRepository.getReferenceById(orderId);
        String response;
        if(newQuantity == 0){
            order.setOrderTotal(order.getOrderTotal() - orderItem.getTotal());
            orderItemRepository.deleteById(orderItemId);
            response = "{" + "\"response\" : \"Quantity updated. Item removed.\"" + "}";
        }else{
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice() *
                    orderItem.getQuantity());
            order.setOrderTotal(order.getOrderTotal() + orderItem.getTotal());
            orderItemRepository.save(orderItem);
            response = "{" + "\"response\" : \"Quantity updated.\"" + "}";
        }
        ordersRepository.save(order);
            return response;
    }

    @Override
    public void addToOrder(@PathVariable Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("service");
        OrderItem orderItem = OrderItem.builder()
                .itemId(menuItemRepository.getReferenceById(menuItemId))
                .quantity(quantity)
                .order(ordersRepository.getReferenceById(orderId)).build();

        orderItem.setTotal(menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                orderItem.getQuantity());
        orderItemRepository.save(orderItem);

//        update order total
        Orders order  = ordersRepository.getReferenceById(orderId);
        order.setOrderTotal(order.getOrderTotal() + (menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                quantity));
        ordersRepository.save(order);
    }

}
