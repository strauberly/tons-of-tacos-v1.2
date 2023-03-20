package com.adamstraub.tonsoftacos.tonsoftacos.controllers.menuItemControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.services.menuItemServices.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class MenuItemController implements MenuItemControllerInterface {
    @Autowired
    private MenuItemService menuItemService;

    @Override
    public Optional<Optional<MenuItem>> getById(String id) {
        System.out.println("controller");
        return menuItemService.findById(id);
    }
//
    @Override
    public List<MenuItem> getByCategory(String category) {
        System.out.println("controller");
        return menuItemService.findByCategory(category);
    }
}