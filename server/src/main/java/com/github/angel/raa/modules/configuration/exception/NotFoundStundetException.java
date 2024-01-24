package com.github.angel.raa.modules.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundStundetException extends HandlerException {
    public NotFoundStundetException(String message, HttpStatus status, int code, LocalDateTime timestamp) {
        super(message, status, code, timestamp);
    }
}
