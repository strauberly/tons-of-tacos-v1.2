package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthServiceInterface {

    @Transactional
    String ownerLogin(OwnerAuthDto ownerAuthDto);
}
