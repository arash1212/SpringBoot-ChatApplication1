package com.example.springbootchatapplication1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionOutput {
    private int id;
    private String message;
//    private String exceptionType;
}
