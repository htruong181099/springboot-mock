package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.dto.request.LoginRequest;
import com.hoangtm14.spring.model.dto.request.RegisterRequest;
import com.hoangtm14.spring.model.dto.response.JwtResponse;
import com.hoangtm14.spring.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> Login(@Valid @RequestBody LoginRequest request) {
        log.info("login" + Constants.BEGIN_API);
        try {
            return ResponseEntity.ok().body(authService.login(request));
        } finally {
            log.info("login" + Constants.END_API);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@Valid @RequestBody RegisterRequest request) {
        log.info("register" + Constants.BEGIN_API);
        try {
            authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(null);
        } finally {
            log.info("register" + Constants.END_API);
        }
    }
}
