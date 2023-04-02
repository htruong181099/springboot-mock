package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.exception.NotFoundException;
import com.hoangtm14.spring.model.dto.request.LoginRequest;
import com.hoangtm14.spring.model.dto.request.RegisterRequest;
import com.hoangtm14.spring.model.dto.response.JwtResponse;
import com.hoangtm14.spring.model.entity.User;
import com.hoangtm14.spring.repository.UserRepository;
import com.hoangtm14.spring.security.CustomUserDetails;
import com.hoangtm14.spring.service.AuthService;
import com.hoangtm14.spring.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        log.info("register" + Constants.BEGIN_SERVICE);
        try {
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            UUID userId = UUID.randomUUID();
            User user = User.builder()
                    .id(userId)
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(hashedPassword)
                    .role(request.getRole())
                    .build();
            userRepository.save(user);
        } finally {
            log.info("register" + Constants.END_SERVICE);
        }
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        log.info("login" + Constants.BEGIN_SERVICE);
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return jwtUtil.generateJwtResponse(userDetails);
        } finally {
            log.info("login" + Constants.END_SERVICE);
        }
    }

    @Override
    public UserDetails getUserById(UUID userId) {
        log.info("getUserById" + Constants.BEGIN_SERVICE);
        try {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new NotFoundException();
            }
            return new CustomUserDetails(user.get());
        } finally {
            log.info("getUserById" + Constants.END_SERVICE);
        }

    }


}
