package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrdersService implements OrdersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public void  createOrder(@RequestBody NewOrderDto order){
        System.out.println("service");
        System.out.println(order);
        Customer newCustomer = order.getCustomer();
        System.out.println(newCustomer);
//        save customer >> get id by name >> set on order
        Orders newOrder = order.getOrder();
        System.out.println(newOrder);
        // see below to calculate new totals and set before saving order


//        set totals for each order item
//        List<OrderItem> newPriceForOrderItems = order.getOrderItems();
//
//
//        for(OrderItem orderItem : newPriceForOrderItems){
//            orderItem.setTotal(orderItem.getQuantity() * menuItemRepository
//                    .getReferenceById(orderItem.getItemId()
//                            .getId()).getUnitPrice());
//
////            calculate total for entire order
//            orderTotal += orderItem.getTotal();
//        }
//        order.setOrderItems(newPriceForOrderItems);
//        order.setOrderTotal(orderTotal);
//        ordersRepository.save(order);
    }



//    @Override
//    @Transactional
//    public void  createOrder(@RequestBody Orders order){
//        System.out.println("service");
//        Double orderTotal = 0.00;
//
////        set totals for each order item
//        List<OrderItem> newPriceForOrderItems = order.getOrderItems();
//
//
//        for(OrderItem orderItem : newPriceForOrderItems){
//            orderItem.setTotal(orderItem.getQuantity() * menuItemRepository
//                    .getReferenceById(orderItem.getItemId()
//                            .getId()).getUnitPrice());
//
////            calculate total for entire order
//            orderTotal += orderItem.getTotal();
//        }
//        order.setOrderItems(newPriceForOrderItems);
//        order.setOrderTotal(orderTotal);
//        ordersRepository.save(order);
//    }


    @Override
    public GetOrdersDto getOrderByUid(String orderUid) {
        System.out.println("service");
        System.out.println(orderUid);
//            get order
        Orders order = ordersRepository.findByOrderUid(orderUid);

        System.out.println(getOrderDtoConverter(order));
        return getOrderDtoConverter(order);
    }


    private GetOrdersDto getOrderDtoConverter(Orders order) {
            GetOrdersDto getOrdersDto = new GetOrdersDto();

            getOrdersDto.setCustomerId(order.getCustomerId());
            getOrdersDto.setOrderTotal(order.getOrderTotal());
            getOrdersDto.setOrderUid(order.getOrderUid());
            getOrdersDto.setCreated(order.getCreated());

            List<GetOrderItemDto> getOrderItemDtos = new ArrayList<>();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> getOrderItemDtos.add(getOrderItemDtoConversion(orderItem)));
            getOrdersDto.setOrderItems(getOrderItemDtos);
            return getOrdersDto;
        }

    private GetOrderItemDto getOrderItemDtoConversion(OrderItem orderItem){
        GetOrderItemDto getOrderItemDto = new GetOrderItemDto();
        getOrderItemDto.setUnitPrice(orderItem.getItemId().getUnitPrice());
        getOrderItemDto.setItemName(orderItem.getItemId().getItemName());
        getOrderItemDto.setTotal(orderItem.getTotal());
        getOrderItemDto.setQuantity(orderItem.getQuantity());
        return getOrderItemDto;
    }
}
