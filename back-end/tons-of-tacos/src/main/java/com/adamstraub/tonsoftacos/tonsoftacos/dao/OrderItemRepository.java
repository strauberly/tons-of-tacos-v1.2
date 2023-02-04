package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderItem", path = "order-item")
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
// get all order items by uuid
//    @GetMapping("/get-items")
    List<OrderItem> orderUuid(@RequestParam("order_uuid") String orderUuid);
// update order item > get order item by uuid and item id
}
