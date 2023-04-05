package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationRequest;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationResponse;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthServiceInterface {

    @Transactional
    AuthenticationResponse ownerLogin(AuthenticationRequest request);
}
