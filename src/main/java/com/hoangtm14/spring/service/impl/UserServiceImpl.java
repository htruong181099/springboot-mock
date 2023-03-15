package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.exception.NotFoundException;
import com.hoangtm14.spring.model.entity.User;
import com.hoangtm14.spring.repository.UserRepository;
import com.hoangtm14.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(UUID userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new NotFoundException();
            }
            return user.get();
        } finally {

        }
    }
}
