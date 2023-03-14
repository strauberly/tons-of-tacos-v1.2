package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByName(@RequestParam("name") String customer);
}
