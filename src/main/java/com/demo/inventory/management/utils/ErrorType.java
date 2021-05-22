package com.demo.inventory.management.utils;

public enum ErrorType
{
    INTERNAL_SERVER_ERROR(101, "INTERNAL_SERVER_ERROR"),
    BAD_REQUEST(102, "BAD_REQUEST"),
    REQUEST_FORBIDDEN(103, "REQUEST_FORBIDDEN"),
    METHOD_NOT_FOUND(104, "METHOD_NOT_FOUND"),
    UNSUPPORTTED_MEDIA_TYPE(105, "UNSUPPORTTED_MEDIA_TYPE"),
    METHOD_NOT_ALLOWED(106, "METHOD_NOT_ALLOWED"),
    UNAUTHORIZED(107, "Unauthorized request"),
    ENTITY_NOT_FOUND(19, "No data found");

    public final Integer code;

    public final String message;

    ErrorType(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

}