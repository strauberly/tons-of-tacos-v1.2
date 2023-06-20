package com.adamstraub.tonsoftacos.tonsoftacos.config.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OwnerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.services.security.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final OwnerRepository ownerRepository;
@Autowired
@Qualifier("handlerExceptionResolver")
@NonNull
private final HandlerExceptionResolver resolver;

//    public JwtAuthFilter(JwtService jwtService, OwnerRepository ownerRepository, HandlerExceptionResolver resolver) {
//        this.jwtService = jwtService;
//        this.ownerRepository = ownerRepository;
//        this.resolver = resolver;
//    }


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

//        try {
//            filterChain.doFilter(request, response);
//        } catch (Exception e) {
////            log.error("Spring Security Filter Chain Exception:", e);
//            resolver.resolveException(request, response, null, e);
//        }
//

try {
    System.out.println("request" + request);
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    String token = null;
    String username = null;
    Date expiration = null;
    Date issuedAt = null;
//        String passwrd = null;
//        System.out.println(authHeader);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
            System.out.println("token = " + token);
        username = jwtService.extractUsername(token);
//            System.out.println("username = " + username);
        expiration = jwtService.extractExpiration(token);
        System.out.println("expiration = " + expiration);
        issuedAt = jwtService.extractIssuedAt(token);
        System.out.println("issued at = " + issuedAt);
    }
////}catch(Exception e){
////    resolver.resolveException(request, response, null, e);
////}
//
//        assert expiration != null;
//        if (!issuedAt.before(expiration)){
//            throw new JwtException("invalid date") ;
//        }
//    System.out.println(username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                System.out.println("encypted username: " + username);
//                System.out.println("decrypted : " + jwtService.decrypt(username));
                UserDetails userDetails = userDetailsService().loadUserByUsername(jwtService.decrypt(username));
//                if (userDetails == null) {
//                    throw new UsernameNotFoundException("Invalid user");
//                }
                System.out.println("token valid: " + jwtService.isTokenValid(token, userDetails));
                //           UserDetails userDetails = userDetailsService().loadUserByUsername(username);
//                jwtService.validateToken(token, userDetails);
                jwtService.isTokenValid(token, userDetails);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null
                        , userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("jwt filter");
//                filterChain.doFilter(request, response);
            }
        filterChain.doFilter(request, response);
//        }catch (ExpiredJwtException e){
}catch (Exception e){
    resolver.resolveException(request, response, null, e);
//    filterChain.doFilter(request, response);
//    throw new JwtException(e.toString());
//            System.out.println(e.getLocalizedMessage());
//            response.setContentType("application/json");
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getOutputStream().println("{ \"error\": \"token issued after expiration\" }");
////    response.getOutputStream().println("{error:" +  e.getMessage()  + "}");
//    response.getOutputStream().println(e.getLocalizedMessage());
//            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "expired" );
//    ResponseWrapper responseWrapper = new ResponseWrapper().fail().msg(e.getMessage());
        }
//        filterChain.doFilter(request, response);
//    }
//
//
//
//
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
////        String passwrd = null;
////        System.out.println(authHeader);
//        if (authHeader!=null && authHeader.startsWith("Bearer ")){
//            token = authHeader.substring(7);
////            System.out.println("token = " + token);
//            username = jwtService.extractUsername(token);
////            System.out.println("username = " + username);
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
//            UserDetails userDetails = userDetailsService().loadUserByUsername(jwtService.decrypt(username));
//            if (userDetails == null){
//                throw new UsernameNotFoundException("Invalid user");
//            }
//            //           UserDetails userDetails = userDetailsService().loadUserByUsername(username);
//            jwtService.validateToken(token, userDetails);
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null
//                    , userDetails.getAuthorities());
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//            System.out.println("jwt filter");
//        }
//        filterChain.doFilter(request, response);
    }

    @Bean
    UserDetailsService userDetailsService(){
        return username -> ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username invalid."));
    }
}

