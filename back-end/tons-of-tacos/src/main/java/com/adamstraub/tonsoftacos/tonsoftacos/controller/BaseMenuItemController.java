package com.adamstraub.tonsoftacos.tonsoftacos.controller;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseMenuItemController implements MenuItemController {
//implments the methods found in the interface
    @Override
    public MenuItem id(Integer id) {
        return null;
    }

}
