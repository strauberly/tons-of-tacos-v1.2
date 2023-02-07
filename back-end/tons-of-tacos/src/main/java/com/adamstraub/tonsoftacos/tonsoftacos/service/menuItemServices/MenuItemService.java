    package com.adamstraub.tonsoftacos.tonsoftacos.service.menuItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class MenuItemService implements MenuItemServiceInterface{
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<MenuItem> findById(Integer id) {
      return Optional.ofNullable(menuItemRepository.id(id));
    }
    @Transactional(readOnly = true)
    @Override
    public List<MenuItem> findByCategory(String category) {
        return menuItemRepository.category(category);
    }


}
