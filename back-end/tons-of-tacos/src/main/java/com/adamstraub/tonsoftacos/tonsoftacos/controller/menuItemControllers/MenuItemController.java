package com.adamstraub.tonsoftacos.tonsoftacos.controller.menuItemControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.menuItemServices.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class MenuItemController implements MenuItemControllerInterface {
    @Autowired
    private MenuItemService menuItemService;

    @Override
    public Optional<MenuItem> getByMenuItemId(Integer id) {
        return menuItemService.findByMenuItemId(id);
    }

    @Override
    public List<MenuItem> getByCategory(String category) {
        return menuItemService.findByCategory(category);
    }
    //implments the methods found in the interface


}