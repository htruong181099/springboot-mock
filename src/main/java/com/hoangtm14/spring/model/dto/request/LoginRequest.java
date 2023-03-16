package com.hoangtm14.spring.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "E_NOT_EMPTY")
    private String username;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String password;
}
