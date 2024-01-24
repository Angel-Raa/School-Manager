package com.github.angel.raa.modules.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Error extends HandlerException{
    public Error(String message, HttpStatus status, int code, LocalDateTime timestamp) {
        super(message, status, code, timestamp);
    }
}
