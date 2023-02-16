package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RepositoryRestResource( collectionResourceRel = "order", path = "order")
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> orderUuid(@RequestParam("orderUuid") String orderUuid);

    Integer deleteByOrderItemId(OrderItem referenceById);

//        @Query(value = "SELECT MAX(item_pk) FROM menu_item")
//    int getReferenceByItemIdMax(OrderItem referenceByItemId);
}
