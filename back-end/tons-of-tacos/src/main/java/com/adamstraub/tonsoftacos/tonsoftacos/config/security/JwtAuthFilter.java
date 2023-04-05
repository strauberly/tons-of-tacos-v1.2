//disabled 4 apr 2023
package com.adamstraub.tonsoftacos.tonsoftacos.config.security;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Owner;
//import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.OwnerInfoOwnerDetailsService;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.AuthService;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private AuthService authService;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

//    private final OwnerInfoOwnerDetailsService ownerInfoOwnerDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
//        has not yet been authenticated
        if (username != null & SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }


//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
//                                   @NonNull FilterChain filterChain)
//            throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        if (authHeader!=null && authHeader.startsWith("Bearer ")){
//            token = authHeader.substring(7);
//            username = loginService.extractUsername(token);
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
//           UserDetails userDetails = ownerInfoOwnerDetailsService.loadUserByUsername(username);
//            if (loginService.validateToken(token, userDetails));
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null
//                    , userDetails.getAuthorities());
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
//        filterChain.doFilter(request, response);
//    }
    }
}
