package com.hoangtm14.spring.exception;


import com.hoangtm14.spring.model.response.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private ErrorResponse error;

    public NotFoundException() {
        this.error = new ErrorResponse("E_404", "Not found");
    }

    public NotFoundException(ErrorResponse error) {
        this.error = error;
    }

    public NotFoundException(String field, String code, String message) {
        this.error = new ErrorResponse(field, code, message);
    }

    public NotFoundException(String code, String message) {
        this.error = new ErrorResponse(code, message);
    }
}
