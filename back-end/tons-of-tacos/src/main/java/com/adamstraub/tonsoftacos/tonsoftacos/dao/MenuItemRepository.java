package com.adamstraub.tonsoftacos.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Repository

//@RepositoryRestResource(collectionResourceRel = "menu", path = "menu")
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
    @NotFound(action= NotFoundAction.IGNORE)
//        Optional<MenuItem> findById(@RequestParam("item_pk") Integer id);
         MenuItem getById(@RequestParam("item_pk")  Integer id);

        List<MenuItem> findByCategory(@RequestParam("category")String category);

        Long countById(int id);
}




