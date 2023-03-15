package com.hoangtm14.spring.controller;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.model.dto.UserSessionDTO;
import com.hoangtm14.spring.model.entity.User;
import com.hoangtm14.spring.service.UserService;
import com.hoangtm14.spring.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity<User> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        log.info("getUser" + Constants.BEGIN_API);
        try {
            UserSessionDTO userDTO = jwtUtil.getUserFromJwt(token);

            return ResponseEntity.ok().body(userService.getUser(userDTO.getUserId()));
        } finally {
            log.info("getUser" + Constants.END_API);
        }
    }
}
