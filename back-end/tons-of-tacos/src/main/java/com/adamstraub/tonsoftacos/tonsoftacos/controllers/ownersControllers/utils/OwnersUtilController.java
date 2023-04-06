package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.utils;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationRequest;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationResponse;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnersUtilController implements OwnersUtilControllerInterface {

    private AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;
//    @Override
//    public AuthenticationResponse ownerLogin(@RequestBody AuthenticationRequest request) {
//        System.out.println("controller");
//        return authService.ownerLogin(request);

//    @Override amigos
//    public AuthenticationResponse ownerLogin(@RequestBody AuthenticationRequest request) {
//        System.out.println("controller");
//      return authService.ownerLogin(request);
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(ownerAuthDto.getUsername(),
//                        ownerAuthDto.getPsswrd()));
//        if (authentication.isAuthenticated()) {
//            return loginService.generateToken(ownerAuthDto.getUsername());
//        }else {
//            throw new UsernameNotFoundException("invalid user request");
//        }
//    }

    @Override
    public String ownerLogin(OwnerAuthDto authDto) {
        System.out.println("controller");
        return authService.ownerLogin(authDto);
    }
}
