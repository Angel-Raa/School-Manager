package com.github.angel.raa.modules.configuration.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundTeacherException extends HandlerException{
    public NotFoundTeacherException(String message, HttpStatus status, int code ) {
        super(message, status, code, LocalDateTime.now());
    }

    public NotFoundTeacherException() {
    }
}
