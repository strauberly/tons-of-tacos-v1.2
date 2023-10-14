package com.adamstraub.tonsoftacos.services.security;

import com.adamstraub.tonsoftacos.dto.businessDto.security.OwnerAuth;

import java.io.UnsupportedEncodingException;
// research possibility of elimination
public interface AuthServiceInterface {

    String ownerLogin(OwnerAuth ownerAuth);
}
