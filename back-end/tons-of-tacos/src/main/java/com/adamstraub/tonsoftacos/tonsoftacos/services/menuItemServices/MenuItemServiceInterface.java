package com.adamstraub.tonsoftacos.tonsoftacos.services.menuItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface MenuItemServiceInterface {
    MenuItem findById(Integer id);

    List<MenuItem> findByCategory(String category);
}
