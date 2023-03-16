package com.adamstraub.tonsoftacos.tonsoftacos.dao;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
//@RepositoryRestResource( collectionResourceRel = "order", path = "order")
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {


    List<OrderItem> order(@RequestParam("order") String order);
    void deleteByOrderItemId(int referenceById);

    OrderItem getByOrderItemId(@RequestParam("orderItemId")Integer orderItemId);

    List<OrderItem> findByOrder(@RequestParam("order")Integer order);
}
