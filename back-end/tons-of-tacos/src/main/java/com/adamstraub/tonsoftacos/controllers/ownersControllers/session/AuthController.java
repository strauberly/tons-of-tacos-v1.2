package com.adamstraub.tonsoftacos.controllers.ownersControllers.session;

import com.adamstraub.tonsoftacos.dto.businessDto.security.OwnerAuthDto;
import com.adamstraub.tonsoftacos.services.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthController implements AuthControllerInterface {


    @Autowired
    AuthService authService;

    @Override
    public String ownerLogin(OwnerAuthDto authDto){
        System.out.println("controller");
        return authService.ownerLogin(authDto);
    }
}
