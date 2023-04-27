package com.adamstraub.tonsoftacos.tonsoftacos.services.menuItemServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class MenuItemService implements MenuItemServiceInterface {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Transactional(readOnly = true)
    @Override
    public Optional<Optional<MenuItem>> findById(String id) {
        System.out.println("service");
        String message = null;
        Optional<MenuItem> menuItem = menuItemRepository.findById(Integer.parseInt(id));

        if(!id.matches("\\d+")){
            throw new NumberFormatException("You have entered invalid data. Try using just a number.");
//            throw new NumberFormatException();
        } else if (menuItem.isEmpty()) {
           throw new NoSuchElementException("You have chosen a menu item that does not exist. Try using an id less than " + menuItemRepository.findAll().size() + ".");
       }else
        return Optional.of(menuItem);
    }


    @Transactional(readOnly = true)
    @Override
    public List<MenuItem> findByCategory(String category) {
            List<MenuItem> menuItems = menuItemRepository.findByCategory(category);
            if (menuItems.isEmpty()){
                throw new NoSuchElementException("You have chosen a category that does not exist. Please check your spelling and consult the docs.");
        }
        return menuItemRepository.findByCategory(category);
    }
}
