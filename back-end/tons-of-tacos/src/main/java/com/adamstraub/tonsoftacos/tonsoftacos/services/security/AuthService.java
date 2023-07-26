package com.adamstraub.tonsoftacos.tonsoftacos.services.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.security.OwnerAuthDto;
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
        System.out.println(ownerAuthDto);
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtService.decrypt(ownerAuthDto.getUsername()),
                            jwtService.decrypt(ownerAuthDto.getPsswrd())));
            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("Bad credentials.");
            }else
                return jwtService.generateToken(ownerAuthDto.getUsername());
    }
}
