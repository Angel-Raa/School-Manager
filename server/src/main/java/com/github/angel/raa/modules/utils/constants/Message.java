package com.github.angel.raa.modules.utils.constants;

public record Message() {
    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";
    public static final String WARNING = "Warning";
    public static final String INFO = "Info";
    public static final String EMPTY = "Empty";
    public static final String NOT_FOUND = "Not found";
    public static final String NOT_ALLOWED = "Not allowed";
    public static final String NOT_VALID = "Not valid";
    public static final String NOT_AUTHORIZED = "Not authorized";
    public static final String  VALIDATE_EMAIL_REGEX = "/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/ ";
    public static final Integer OK = 200;
    public static final Integer CREATED = 201;
    public static final Integer NO_CONTENT = 204;
    public static final Integer BAD_REQUEST = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer FORBIDDEN = 403;
    public static final Integer NOT_FOUND_HTTP = 404;
    public static final Integer INTERNAL_SERVER_ERROR = 500;
}