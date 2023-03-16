package com.hoangtm14.spring.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {
    @NotEmpty(message = "E_NOT_EMPTY")
    private String username;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String password;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String name;
    @NotEmpty(message = "E_NOT_EMPTY")
    private String role;

}
