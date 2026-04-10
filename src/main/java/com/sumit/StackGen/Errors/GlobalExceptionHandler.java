package com.sumit.StackGen.Errors;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage());
        log.error(apiError.toString(), ex);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex){
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND,ex.getMessage());
        log.error(apiError.message(),ex);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }
}
