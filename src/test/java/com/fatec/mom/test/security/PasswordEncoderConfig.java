package com.fatec.mom.test.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderConfig {

    @Test
    public void givenAnEncoderPasswordMustReturnAnEncryptedPassword() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);

        String encryptedPassword = encoder.encode("password");
        System.out.println(encryptedPassword);
    }
}
