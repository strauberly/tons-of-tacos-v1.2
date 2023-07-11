package com.adamstraub.tonsoftacos.tonsoftacos.services.menuItemServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import jakarta.persistence.EntityNotFoundException;
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
    @Transactional
    @Override
    public MenuItem findById(Integer id) {
        System.out.println("service");
        String message = null;
        MenuItem menuItem;

        try{
            menuItem = menuItemRepository.findById(id).orElseThrow();
        }catch (Exception e){
//            System.out.println(e.getMessage());
            throw new EntityNotFoundException("You have chosen a menu item that does not exist.");
        }

//        if(!id.matches("\\d+")){
//            throw new IllegalArgumentException("You have entered invalid data. Try using just a number.");
////            throw new NumberFormatException();
//        } else if (menuItem.isEmpty()) {
//           throw new EntityNotFoundException("You have chosen a menu item that does not exist. Try using an id less than " + menuItemRepository.findAll().size() + ".");
//       }else
        return menuItem;
    }


    @Transactional(readOnly = true)
    @Override
    public List<MenuItem> findByCategory(String category) {
        System.out.println("service");
            List<MenuItem> menuItems = menuItemRepository.findByCategory(category);
            if (menuItems.isEmpty()){
                throw new EntityNotFoundException("You have chosen a category that does not exist. Please check your spelling, formatting, and consult the docs.");
        }
        return menuItemRepository.findByCategory(category);
    }
}
