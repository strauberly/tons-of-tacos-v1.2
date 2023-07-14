package com.adamstraub.tonsoftacos.tonsoftacos.services.security;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.security.OwnerAuthDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

public interface AuthServiceInterface {

    @Transactional
    String ownerLogin(OwnerAuthDto ownerAuthDto) throws UnsupportedEncodingException;
}
