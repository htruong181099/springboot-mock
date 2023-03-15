package com.hoangtm14.spring.exception;

import com.hoangtm14.spring.model.dto.response.ErrorResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
public class SpringExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleInternalServerError(Exception e) {
        log.error("Exception", e);
        ErrorResponse error = new ErrorResponse("E_500", "Internal Server error");
        return ResponseEntity.internalServerError().body(error);
    }

    @ResponseBody
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException", e);
        return ResponseEntity.badRequest().body(e.getError());
    }

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
    }

    @ResponseBody
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<?> handleUnAuthorizedException(UnauthorizedException e) {
        log.error("UnAuthorizedException", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getError());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptionValidationException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        String field = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .findFirst()
                .orElse(e.getFieldError().getField());
        String errorCode = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(e.getMessage());
        String message = "Validation error";
        ErrorResponse errorResponse = new ErrorResponse(field, errorCode, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
        log.error("BadCredentialsException", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("E_BAD_CREDENTIALS", "Bad Credentials"));
    }


}
