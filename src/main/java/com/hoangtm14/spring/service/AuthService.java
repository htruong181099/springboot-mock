package com.hoangtm14.spring.service;

import com.hoangtm14.spring.model.dto.request.LoginRequest;
import com.hoangtm14.spring.model.dto.request.RegisterRequest;
import com.hoangtm14.spring.model.dto.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AuthService {
    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);

    UserDetails getUserById(UUID userId);
}
