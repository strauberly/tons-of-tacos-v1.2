package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderUuid(@RequestParam("orderUuid") String orderUuid);

    void deleteById(OrderItem referenceById);
}
