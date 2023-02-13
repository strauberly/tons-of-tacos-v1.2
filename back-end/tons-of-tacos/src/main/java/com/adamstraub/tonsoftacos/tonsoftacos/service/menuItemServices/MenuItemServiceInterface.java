package com.adamstraub.tonsoftacos.tonsoftacos.service.menuItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface MenuItemServiceInterface {
    @Transactional(readOnly = true)
    Optional<Optional<MenuItem>> findById(Integer id);

    @Transactional(readOnly = true)
    List<MenuItem> findByCategory(String category);
}
