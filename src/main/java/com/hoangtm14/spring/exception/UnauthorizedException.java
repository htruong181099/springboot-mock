package com.hoangtm14.spring.exception;


import com.hoangtm14.spring.model.dto.response.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private ErrorResponse error;

    public UnauthorizedException(ErrorResponse error) {
        this.error = error;
    }

    public UnauthorizedException(String field, String code, String message) {
        this.error = new ErrorResponse(field, code, message);
    }

    public UnauthorizedException(String code, String message) {
        this.error = new ErrorResponse(code, message);
    }
}
