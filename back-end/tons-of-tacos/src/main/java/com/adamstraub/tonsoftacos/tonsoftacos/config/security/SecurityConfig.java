package com.adamstraub.tonsoftacos.tonsoftacos.config.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
@Autowired
    private final JwtAuthFilter authFilter;

    private final UserDetailsService userDetailsService;

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter chain");
        return

                http
                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .csrf().disable()
//                whitelisted
                        .authorizeHttpRequests()
                        .requestMatchers("/api/menu/**", "/api/order/**", "/api/owners-tools/login").permitAll()
                        .and()
//               restricted
                        .authorizeHttpRequests().requestMatchers("/api/owners-tools/**")
                        .authenticated().and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authenticationProvider(authenticationProvider())
//                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();

//
//

//            return
//                http
//                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                        .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
//                        .and()
////                            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                        .csrf().disable()
////                    http
//////                            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//////                            .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
//////                            .and()
//////                            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
////                            .csrf().disable()
//
////                whitelisted
//                            .authorizeHttpRequests()
//                            .requestMatchers("/api/menu/**", "/api/order/**", "/api/owners-tools/login").permitAll()
//                            .and()
////               restricted
//                            .authorizeHttpRequests().requestMatchers("/api/owners-tools/**")
//                            .authenticated().and()
//                            .sessionManagement()
//                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                            .and()
//                            .authenticationProvider(authenticationProvider())
////                            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                            .build();
    }

    @Bean
    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }
}