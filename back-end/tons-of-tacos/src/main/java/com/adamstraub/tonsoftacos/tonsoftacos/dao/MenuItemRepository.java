package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "menuItems", path = "menu-item-search")
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
        MenuItem id(@RequestParam("id") Integer id);
        List<MenuItem> category(@RequestParam("category")String category);
}




