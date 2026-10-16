package com.example.SOCApplication.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> HandleException(LoginException ex){
        return ResponseEntity.
                status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }


}
