package com.github.angel.raa.modules.configuration.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NotFoundSubjectsException extends HandlerException{
    public NotFoundSubjectsException(String message, HttpStatus status, int code) {
        super(message, status, code,LocalDateTime.now());
    }
}
