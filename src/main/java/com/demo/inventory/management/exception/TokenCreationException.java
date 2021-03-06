package com.demo.inventory.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenCreationException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public TokenCreationException(String message)
    {
        super(message);
    }

    public TokenCreationException(String message, Throwable cause)
    {
        super(message, cause);
    }

}