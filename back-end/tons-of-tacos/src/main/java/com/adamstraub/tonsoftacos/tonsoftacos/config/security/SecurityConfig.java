package com.adamstraub.tonsoftacos.tonsoftacos.config.security;

//import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.OwnerInfoOwnerDetailsService;
//import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.OwnerInfoOwnerDetailsService;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.OwnerInfoOwnerDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
    private JwtAuthFilter authFilter;

//    private final AuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsService userDetailsService(){
        return new OwnerInfoOwnerDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println("filter chain");
        return
                http.csrf().disable()
//        whitelisted
                        .authorizeHttpRequests()
                        .requestMatchers("/api/menu/**", "/api/order/checkout", "/api/owners-tools/login").permitAll()
              .and()
                .authorizeHttpRequests().requestMatchers("/api/owners-tools/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


//        return amigos code
//                http.csrf().disable()
////        whitelisted
//                        .authorizeHttpRequests()
//                        .requestMatchers("/api/menu/**", "/api/order/checkout", "/api/owners-tools/login").permitAll()
////                .and()
////                require auth
////                .authorizeHttpRequests().requestMatchers("/api/owners-tools/**")
////                update to no form once we can verify all functions with form >> just remove .formlogin
//                        .anyRequest()
//                        .authenticated()
//                        .and()
//                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .and()
//                        .authenticationProvider(authenticationProvider)
//                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                        .build();
////        System.out.println("filter chain");




//        return securityFilterChain;
////        System.out.println("filter chain");
//        SecurityFilterChain securityFilterChain =
//        http.csrf().disable()
////        whitelisted
//                .authorizeHttpRequests()
//                .requestMatchers("/api/menu/**", "/api/order/checkout", "/api/owners-tools/login").permitAll()
////                .and()
////                require auth
////                .authorizeHttpRequests().requestMatchers("/api/owners-tools/**")
////                update to no form once we can verify all functions with form >> just remove .formlogin
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//        System.out.println("filter chain");
//        return securityFilterChain;

    }
// moved to application config in amigos example
    @Bean
    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }
}