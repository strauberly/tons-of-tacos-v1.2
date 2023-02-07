package com.adamstraub.tonsoftacos.tonsoftacos.service.menuItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;


import java.util.List;
import java.util.Optional;

public interface MenuItemServiceInterface {
    Optional<MenuItem> findById(Integer id);

    List<MenuItem> findByCategory(String category);
}
