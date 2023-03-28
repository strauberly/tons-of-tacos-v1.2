package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
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
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public void  createOrder(@RequestBody NewOrderDto order){
        System.out.println("service");
//        System.out.println(order);

        Double orderTotal = 0.00;

        Orders newOrder = order.getOrder();
//        System.out.println(newOrder);
        List<OrderItem> orderItems = newOrder.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setTotal(orderItem.getQuantity() *
                    menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
            orderTotal += orderItem.getTotal();
        }

        Customer newCustomer = order.getCustomer();
//        System.out.println(newCustomer);
        if (customerRepository.findByName(newCustomer.getName()) != null) {
            newOrder.setCustomerId(customerRepository.findByName(newCustomer.getName()).getCustomerId());
        }else{
            customerRepository.save(newCustomer);
            newCustomer = customerRepository.findByName(newCustomer.getName());
//            System.out.println(newCustomer);
            newOrder.setCustomerId(newCustomer.getCustomerId());
        }
        newOrder.setOrderItems(orderItems);
        newOrder.setOrderTotal(orderTotal);
        System.out.println("Order created.");
        ordersRepository.save(newOrder);
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
