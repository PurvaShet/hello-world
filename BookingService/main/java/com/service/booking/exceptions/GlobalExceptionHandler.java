package com.service.booking.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleAndPropagate(Exception ex) throws Exception {
        System.out.println("Intercepted in global handler: " + ex.getMessage());
        throw ex;
    }
}
