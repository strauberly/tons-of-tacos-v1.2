package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OwnerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OwnerDetailsService implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ownerRepository.findByUsername(username);
    }
}
