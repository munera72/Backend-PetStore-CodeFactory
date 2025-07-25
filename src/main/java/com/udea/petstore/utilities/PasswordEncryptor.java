package com.udea.petstore.utilities;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class PasswordEncryptor {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
