package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.ReturnOrderToCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class OrdersService implements OrdersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private boolean customerNameValid = false;
    private boolean customerPhoneNumberValid = false;
    private boolean customerEmailValid = false;

    @Override
    @Transactional
    public ReturnOrderToCustomerDto createOrder(@RequestBody @NotNull NewOrderDto order) {
        System.out.println("service");
        System.out.println("new order received: " + order);

        Double orderTotal = 0.00;
        ReturnOrderToCustomerDto customerCopyDto = new ReturnOrderToCustomerDto();
        Orders newOrder = order.getOrder();
        Orders orderConfirmation;
        List<OrderItem> orderItems = newOrder.getOrderItems();
        List<GetOrderItemDto> orderItemDtos = new ArrayList<>();

//        order validation
        System.out.println("validation check");
        System.out.println("customer name: " + order.getCustomer().getName());
        validateCustomerName(order.getCustomer().getName());
        System.out.println("customer name valid: " + customerNameValid);

        System.out.println("customer phone: " + order.getCustomer().getPhoneNumber());
        validateCustomerPhone(order.getCustomer().getPhoneNumber());
        System.out.println("customer phone valid: " + customerPhoneNumberValid);

        System.out.println("customer email: " + order.getCustomer().getEmail());
        validateCustomerEmail(order.getCustomer().getEmail());
        System.out.println("customer email valid: " + customerEmailValid);


            if (!customerNameValid) {
                throw new IllegalArgumentException("Customer name incorrectly formatted. Please consult the documentation.");
            }
            if(!customerPhoneNumberValid) {
                throw new IllegalArgumentException("Customer phone number incorrectly formatted. Please consult the documentation.");
            }
            if (!customerEmailValid){
                throw new IllegalArgumentException("Customer e-mail incorrectly formatted. Please consult the documentation.");
            }
            if (!(order.getOrder().getOrderItems().size() > 0)) {
            throw new IllegalArgumentException("An order must contain at least 1 menu item and must not be null. Please consult the documentation.");
            }

            Customer newCustomer = order.getCustomer();
//                if customer already exists, use existing customer id else create new customer
            if (customerRepository.findByName(newCustomer.getName()) != null &&
                    Objects.equals
                            (customerRepository.findByName(newCustomer.getName()).getEmail(),
                                    order.getCustomer().getEmail())
            && Objects.equals(customerRepository.findByName(newCustomer.getName()).getPhoneNumber(),
                    order.getCustomer().getPhoneNumber())) {
                newOrder.setCustomerId(customerRepository.findByName(newCustomer.getName()).getCustomerId());
            } else {
                customerRepository.save(newCustomer);
                newCustomer = customerRepository.findByName(newCustomer.getName());
                newOrder.setCustomerId(newCustomer.getCustomerId());
            }

//            set order items and total
            newOrder.setOrderItems(orderItems);

            for (OrderItem orderItem : orderItems) {
            orderItem.setTotal(orderItem.getQuantity() *
                    menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
            orderTotal += orderItem.getTotal();
        }
            newOrder.setOrderTotal(orderTotal);

//        set order uid
        newOrder.setOrderUid(genOrderUid());
        ordersRepository.save(newOrder);
        System.out.println("Order created.");

//        reset valid flags

        customerNameValid = false;
        customerPhoneNumberValid = false;
        customerEmailValid = false;
//        boolean orderBodyValid = false;


//create an order confirmation
            orderConfirmation = ordersRepository.findByOrderUid(newOrder.getOrderUid());
            customerCopyDto.setCustomerName(newCustomer.getName());
            customerCopyDto.setOrderUid(newOrder.getOrderUid());
            customerCopyDto.setOrderTotal(newOrder.getOrderTotal());

            orderItems = orderConfirmation.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItemDtos.add(orderItemDtoConvertor(orderItem));
            }
            customerCopyDto.setOrderItems(orderItemDtos);
        return customerCopyDto;
    }


    private GetOrderItemDto orderItemDtoConvertor(OrderItem orderItem) {
        GetOrderItemDto orderItemDto = new GetOrderItemDto();

        orderItemDto.setItemName(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getItemName());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setTotal(orderItem.getTotal());
        orderItemDto.setUnitPrice(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());

        return orderItemDto;
    }

    private String genOrderUid() {
        // desired example result: 11A32
        String orderUid = null;
        StringBuilder orderUidBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            orderUid = String.valueOf(orderUidBuilder.append(randomUidChar()));
        }
//        compare uid against others by doing a find by uid and if null then return if not re-run
        if (ordersRepository.findByOrderUid(orderUid) != null) {
            genOrderUid();
        }
        return orderUid;
    }

    private char randomUidChar() {
        int min = 48, max = 90;
        int random = (int) (Math.random() * ((max - min)) + min);
        char randomChar;
        int[] excluded = {58, 59, 60, 61, 62, 63, 64};
        if (ArrayUtils.contains(excluded, random)) {
            randomChar = randomUidChar();
            return randomChar;
        }
        return (char) random;
    }

    private void validateCustomerName(String customerName) {
        byte[] nameChars = customerName.getBytes(StandardCharsets.UTF_8);
        int spaces = 0;
        for (Byte namechar : nameChars) {
            if (Objects.equals(namechar, (byte) 32)) {
                spaces += 1;
            }
        }
//        possibly alter for just ^[a-zA-Z]$+ [a-zA-Z]+. currently accepting letters from any language.
        if (customerName.matches("^\\p{L}+[\\p{L}\\p{Pd}\\p{Zs}']*\\p{L}+$|^\\p{L}+$") &&
                    spaces == 1) {
                customerNameValid = true;
        }
    }

    private void validateCustomerPhone(String customerPhone){
        byte [] phoneDigits = customerPhone.getBytes(StandardCharsets.UTF_8);
        if (customerPhone.matches("[0-9-]*")
                && customerPhone.charAt(3) == (byte) 45
                && customerPhone.charAt(7) == 45
                && customerPhone.length()==12){
            customerPhoneNumberValid = true;
        }
    }
    private void validateCustomerEmail(String customerEmail){
        if (customerEmail.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}")) customerEmailValid = true;
    }

}
