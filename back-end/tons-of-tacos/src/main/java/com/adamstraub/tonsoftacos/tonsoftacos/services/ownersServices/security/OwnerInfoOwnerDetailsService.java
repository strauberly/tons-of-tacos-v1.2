package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security;

import com.adamstraub.tonsoftacos.tonsoftacos.config.security.UserInfoUserDetails;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OwnerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OwnerInfoOwnerDetailsService implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> ownerInfo = ownerRepository.findByUsername(username);
        return ownerInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
    }
}
