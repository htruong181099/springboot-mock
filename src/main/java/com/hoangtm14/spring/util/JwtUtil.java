package com.hoangtm14.spring.util;

import com.hoangtm14.spring.model.dto.UserSessionDTO;
import com.hoangtm14.spring.model.dto.response.JwtResponse;
import com.hoangtm14.spring.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtUtil {

    public static final String BEARER = "Bearer ";
    public static final String USER = "user";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String ROLE = "role";

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private String getJwtSecret() {
        return this.jwtSecret;
    }

    public LocalDateTime generateExpirationDate() {
        return LocalDateTime.now().plus(jwtExpiration, ChronoField.MILLI_OF_DAY.getBaseUnit());
    }

    public JwtResponse generateJwtResponse(CustomUserDetails userDetails) {
        LocalDateTime expiryDateTime = generateExpirationDate();
        Date expiryDate = Date.from(expiryDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return JwtResponse.builder()
                .accessToken(generateJwtToken(userDetails, expiryDate))
                .expiresAt(expiryDateTime)
                .build();
    }

    public String generateJwtToken(CustomUserDetails userDetails, Date expirationDate) {
        Claims claims = Jwts.claims().setSubject(USER);
        claims.put(USER_ID, userDetails.getId());
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(ROLE, userDetails.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, getJwtSecret())
                .compact();
    }

    public UserSessionDTO getUserFromJwt(String token) {
        if (Strings.hasText(token) && token.startsWith(BEARER)) {
            token = token.substring(BEARER.length());
        }
        Claims claims = Jwts.parser()
                .setSigningKey(getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setUserId(UUID.fromString(claims.get(USER_ID).toString()));
        userSessionDTO.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority((String) claims.get(ROLE))));
        return userSessionDTO;
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEARER.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        return true;
    }
}
