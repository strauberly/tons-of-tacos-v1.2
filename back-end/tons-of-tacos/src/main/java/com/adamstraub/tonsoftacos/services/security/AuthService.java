package com.adamstraub.tonsoftacos.services.security;
import com.adamstraub.tonsoftacos.dto.businessDto.security.OwnerAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


//service pertains to authentication functions(login, logout, session timeout etc.)
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public String ownerLogin(OwnerAuthDto ownerAuthDto) {
        System.out.println("auth service");
//        System.out.println(ownerAuthDto);
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtService.decrypt(ownerAuthDto.getUsername()),
                            jwtService.decrypt(ownerAuthDto.getPsswrd())));
//            if (!authentication.isAuthenticated()) {
//                throw new BadCredentialsException("Bad credentials." + jwtService.decrypt(ownerAuthDto.getUsername()) + " " +  jwtService.decrypt(ownerAuthDto.getPsswrd()));
        }catch (Exception e) {
//            throw new BadCredentialsException("Bad credentials. " + "username: " + jwtService.decrypt(ownerAuthDto.getUsername()) + " " + "password: " + jwtService.decrypt(ownerAuthDto.getPsswrd()));
            throw new BadCredentialsException("Bad credentials. " + "username: " + ownerAuthDto.getUsername() + " " + "password: " + ownerAuthDto.getPsswrd());

        }
//        }else
                return jwtService.generateToken(ownerAuthDto.getUsername());
    }
}
