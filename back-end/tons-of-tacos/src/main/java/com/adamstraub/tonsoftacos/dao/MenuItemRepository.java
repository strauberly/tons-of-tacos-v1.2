package com.adamstraub.tonsoftacos.dao;

import com.adamstraub.tonsoftacos.entities.MenuItem;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
//    @NotFound(action= NotFoundAction.IGNORE)
//        Optional<MenuItem> findById(@RequestParam("item_pk") Integer id);
         MenuItem getById(@RequestParam("item_pk")  Integer id);

        List<MenuItem> findByCategory(@RequestParam("category")String category);

        Long countById(int id);
}




