package com.fatec.mom.infra.security.jwt;

import lombok.Data;

@Data
public class UsernamePasswordAuthenticationRequest {

    private String username;
    private String password;
}
