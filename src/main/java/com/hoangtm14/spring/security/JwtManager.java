package com.hoangtm14.spring.security;

import com.hoangtm14.spring.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtManager {
    public static final String TOKEN_PREFIX = "Bearer";
    public final JwtUtil jwtUtil;
    

}
