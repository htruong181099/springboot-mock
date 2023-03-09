package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.repository.UserRepository;
import com.hoangtm14.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

}
