package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "menu", path = "menu")
public
interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
        Optional<MenuItem> findById(@RequestParam("item_pk") Integer id);

        List<MenuItem> category(@RequestParam("category")String category);
}




