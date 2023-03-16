package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Repository
//@RepositoryRestResource( collectionResourceRel = "orders", path = "orders")
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findByOrderUid(@RequestParam ("order_uid") String orderUid);
    List<Orders> findByCustomerId(@RequestParam("customer_fk") Integer customerId);
    List<Orders> findByStatus( String status);
}
