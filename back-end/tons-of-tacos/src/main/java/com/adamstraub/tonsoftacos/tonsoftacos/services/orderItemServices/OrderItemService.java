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

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

//    @Override
//    @Transactional
//    public void addToCart(@RequestBody OrderItem orderItem) throws InvalidPropertiesFormatException {
//        System.out.println("service");
////        System.out.println(cartItem);
//        orderItem.setOrderItemId(0);
//        orderItem.setTotal(orderItem.getQuantity() *
//                menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
//
////        validation needs to be re-addressed after linking to orders
////
////        if (cartItem.getItemId().getId() > menuItemRepository.count()) {
////            throw new NoSuchElementException("A menu item with that id does not exist.");
////        }
////        if (!cartItem.getItemId().toString().matches(".*\\d.*")) {
////            throw new NumberFormatException("You have entered in invalid menu item id.");
////        }
////        if (!cartItem.getOrder().matches("([0-9\\-]+)")) {
////            throw new InvalidPropertiesFormatException("You have entered an invalid cart id.");
////        }
////        if (!cartItem.getQuantity().toString().matches(".*\\d.*")) {
////            throw new NumberFormatException("You have entered in invalid quantity.");
////        }
////        if (!cartItem.getTotal().equals(cartItem.getTotal().doubleValue())) {
////            throw new NumberFormatException("You have enter an invalid format for total");
////        }
////        System.out.println(orderItem);
//        orderItemRepository.save(orderItem);
//    }

//    @Override
//    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {
//
//        System.out.println("service");
//        List<OrderItem> orderItems = orderItemRepository.order(cartUuid);
////        System.out.println("orderitems = " + orderItems);
//        List<GetOrderItemDto> orderItemDtos = new ArrayList<>();
//        if (orderItems.isEmpty()) {
//            throw new NoSuchElementException("No cart exists with that id");
//        } else {
//            for (OrderItem orderItem : orderItems) {
//                orderItemDtos.add(getOrderItemDtoConversion(orderItem));
//            }
//        }
//        System.out.println("Retrieved a cart.");
////        System.out.println(orderItemDtos);
//        return orderItemDtos;
//    }

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
//            ordersRepository.save(order);
            orderItemRepository.deleteById(orderItemId);
            response = "{" + "\"response\" : \"Quantity updated. Item removed.\"" + "}";
//            return "{" + "\"response\" : \"Quantity updated. Item removed.\"" + "}";
        }else{
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice() *
                    orderItem.getQuantity());
            order.setOrderTotal(order.getOrderTotal() + orderItem.getTotal());
//            ordersRepository.save(order);
            orderItemRepository.save(orderItem);
            response = "{" + "\"response\" : \"Quantity updated.\"" + "}";
//            return "{" + "\"response\" : \"Quantity updated.\"" + "}";
//        orderItemRepository.deleteById(orderItemId);
        }
        ordersRepository.save(order);
            return response;
    }
//
//@Override
//@Transactional
//public OrderItemDto updateCart(@PathVariable Integer orderItemId, @PathVariable Integer newQuantity) {
//    System.out.println("service");
//    OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
//    MenuItem menuItem = menuItemRepository.getReferenceById(orderItem.getItemId().getId());
//    OrderItemDto orderItemDto = null;
//    if (newQuantity == 0) {
//        orderItemRepository.save(orderItem);
//        orderItemRepository.deleteByCartItemId(orderItemId);
//        orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
//        System.out.println("Cart updated. Item removed.");
//    } else {
//        orderItem.setQuantity(newQuantity);
//        orderItem.setTotal(orderItem.getQuantity() * menuItem.getUnitPrice());
//        orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
//        orderItemRepository.save(orderItem);
//        System.out.println("Cart item updated.");
////        System.out.println(orderItemDto);
//    }
////    orderItemDto.setOrder("NA");
//    return orderItemDto;
//}


//    @Override
//    @Transactional
//    public void removeCartItem(@PathVariable Integer orderItemId) {
//        System.out.println("service");
//        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
////        System.out.println(orderItem);
//        if (orderItem.getItemId() == null) {
//            throw new NoSuchElementException("This order id does not exist.");
//        } else {
//            System.out.println("Cart item removed.");
//            orderItemRepository.deleteByCartItemId(Math.toIntExact(orderItem.getOrderItemId()));
//
//        }
//    }

    @Override
    public void addToOrder(@PathVariable Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("service");
//        System.out.println("order id: " + orderId);
//        System.out.println("menu item id: " + menuItemId);
//        System.out.println("quantity: " + quantity);
        OrderItem orderItem = OrderItem.builder()
                .itemId(menuItemRepository.getReferenceById(menuItemId))
                .quantity(quantity)
                .order(ordersRepository.getReferenceById(orderId)).build();

        orderItem.setTotal(menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                orderItem.getQuantity());
//        System.out.println(orderItem);
        orderItemRepository.save(orderItem);
//
//        update order total
        Orders order  = ordersRepository.getReferenceById(orderId);
        order.setOrderTotal(order.getOrderTotal() + (menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                quantity));
        ordersRepository.save(order);

//
//        Orders order = ordersRepository.getReferenceById(orderId);
//         calculateOrderTotal(order);
//        ordersRepository.save(order);
//        Double newTotal = 0.00;
//        Orders order = ordersRepository.getReferenceById(orderId);
//        List<OrderItem> orderItems = new ArrayList<>(order.getOrderItems());
//        for (OrderItem savedOrderItem: orderItems){
//            newTotal += savedOrderItem.getTotal();
//        }
//        order.setOrderTotal(newTotal);
    }

//    private void calculateOrderTotal(Orders order){
//        Double newTotal = 0.00;
////        Orders order = ordersRepository.getReferenceById(orderId);
//        List<OrderItem> orderItems = new ArrayList<>(order.getOrderItems());
//        for (OrderItem savedOrderItem: orderItems){
//            newTotal += savedOrderItem.getTotal();
//        }
//        order.setOrderTotal(newTotal);
//        ordersRepository.save(order);
//    }

    public GetOrderItemDto getOrderItemDtoConversion(OrderItem orderItem){
        GetOrderItemDto getOrderItemDto = new GetOrderItemDto();
        getOrderItemDto.setUnitPrice(orderItem.getItemId().getUnitPrice());
        getOrderItemDto.setItemName(orderItem.getItemId().getItemName());
        getOrderItemDto.setTotal(orderItem.getTotal());
        getOrderItemDto.setQuantity(orderItem.getQuantity());
//        System.out.println(getOrderItemDto);
        return getOrderItemDto;
    }
}
