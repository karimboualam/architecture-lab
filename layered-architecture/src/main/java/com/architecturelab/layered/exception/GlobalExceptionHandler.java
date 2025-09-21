package com.architecturelab.layered.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "error", ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
