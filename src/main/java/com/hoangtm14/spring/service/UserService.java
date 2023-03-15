package com.hoangtm14.spring.service;

import com.hoangtm14.spring.model.entity.User;

import java.util.UUID;

public interface UserService {
    User getUser(UUID userId);
}
