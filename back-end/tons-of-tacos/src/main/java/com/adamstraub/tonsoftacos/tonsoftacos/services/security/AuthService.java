package com.adamstraub.tonsoftacos.tonsoftacos.services.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//service pertains to authentication functions(login, logout, session timeout etc.)
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
        @Override
    public String ownerLogin(OwnerAuthDto ownerAuthDto) {
        System.out.println("service");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(ownerAuthDto.getUsername(),
                        ownerAuthDto.getPsswrd()));
        if (authentication.isAuthenticated()) {
        return jwtService.generateToken(ownerAuthDto.getUsername());
    }else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }
}
