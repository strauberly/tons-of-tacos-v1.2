package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
//    maybe not private
    private String psswrd;
}
