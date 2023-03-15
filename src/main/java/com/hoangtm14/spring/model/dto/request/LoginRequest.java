package com.hoangtm14.spring.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "VL")
    private String username;
    @NotEmpty(message = "VL")
    private String password;
}
