package com.hoangtm14.spring.exception;

import com.hoangtm14.spring.model.dto.response.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorResponse error;

    public BadRequestException(ErrorResponse error) {
        this.error = error;
    }

    public BadRequestException(String field, String code, String message) {
        this.error = new ErrorResponse(field, code, message);
    }

    public BadRequestException(String code, String message) {
        this.error = new ErrorResponse(code, message);
    }

}
