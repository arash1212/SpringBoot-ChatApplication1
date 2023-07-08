package com.example.springbootchatapplication1.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionOutput> handle(CustomException e) {
        String message = environment.getProperty("e." + e.getId());
        ExceptionOutput output = new ExceptionOutput(e.getId(), message);
        return new ResponseEntity<>(output, HttpStatus.BAD_REQUEST);
    }
}
