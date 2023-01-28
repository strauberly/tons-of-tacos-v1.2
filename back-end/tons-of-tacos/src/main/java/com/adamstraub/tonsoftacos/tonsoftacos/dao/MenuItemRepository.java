package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "menuItems", path = "menu-item")
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{

        MenuItem id(@RequestParam("id") Integer id);
        Page<MenuItem> category(@RequestParam("category")String category, Pageable pageable);
}




