package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerAuthDto {
    private String username;

    private String psswrd;
}
