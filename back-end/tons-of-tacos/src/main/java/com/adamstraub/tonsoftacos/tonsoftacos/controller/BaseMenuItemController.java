package com.adamstraub.tonsoftacos.tonsoftacos.controller;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseMenuItemController implements MenuItemController {


    @Override
    public MenuItem fetchMenuItem(Long id) {
        return null;
    }
}
