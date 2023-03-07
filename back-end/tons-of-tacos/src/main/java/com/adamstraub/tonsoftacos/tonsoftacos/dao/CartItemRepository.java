package com.adamstraub.tonsoftacos.tonsoftacos.dao;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@RepositoryRestResource( collectionResourceRel = "order", path = "order")
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {


    List<CartItem> cartUuid(@RequestParam("cartUuid") String cartUuid);
    void deleteByOrderItemId(int referenceById);

    CartItem getByItemId(@RequestParam("orderItemId")Integer itemId);
}
