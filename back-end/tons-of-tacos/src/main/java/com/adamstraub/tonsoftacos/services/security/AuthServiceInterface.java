package com.adamstraub.tonsoftacos.services.security;

import com.adamstraub.tonsoftacos.dto.businessDto.security.OwnerAuthDto;

import java.io.UnsupportedEncodingException;

public interface AuthServiceInterface {

    String ownerLogin(OwnerAuthDto ownerAuthDto) throws UnsupportedEncodingException;
}
