package com.adamstraub.tonsoftacos.tonsoftacos.config.security;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoUserDetails implements UserDetails {
    private String username;
    private String psswrd;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(Owner ownerInfo){
        username = ownerInfo.getUsername();
        psswrd = ownerInfo.getPsswrd();
        authorities = Arrays.stream(ownerInfo.getRole().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return psswrd;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
