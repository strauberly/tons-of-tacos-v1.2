package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
//@RepositoryRestResource( collectionResourceRel = "orders", path = "orders")
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findByOrderUid(@RequestParam ("order_uid") String orderUid);
    List<Orders> findByCustomerId(@RequestParam("customer_fk") Integer customerId);

    default List<Orders> findByClosed(){
        List<Orders> closedOrders = new ArrayList<>();
        List<Orders> orders = findAll();
        System.out.println(orders);
        for (Orders order: orders){
            if (!order.getClosed().equals("no")){
                closedOrders.add(order);
            }
        }
        System.out.println(closedOrders);
        return closedOrders;
    }

    Orders getById(@RequestParam("order_pk") Integer orderId);

}
