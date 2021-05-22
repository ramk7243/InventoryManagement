package com.demo.inventory.management.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.model.response.ErrorResponse;
import com.demo.inventory.management.model.response.Response;

@Component
public class InventoryUtils
{

    public static Response generateErrorResponse(String errorMessage, HttpStatus httpStatus, ErrorType error)
    {
        return new ErrorResponse(error.code, errorMessage);
    }
    
    public static Response generateErrorResponse(ErrorType error, String errorMessage)
    {
        return new ErrorResponse(error.code, errorMessage);
    }
    
    public static Response generateErrorResponse(ErrorType error)
    {
        return new ErrorResponse(error.code, error.message);
    }

    public static Response getSuccessResponse(String message)
    {
        return new Response(message);
    }

    public static Response getObjectResponse(Object object)
    {
        return new Response(object);
    }

    public static Response getZeroRecordResponse(String message)
    {
        return new ErrorResponse(message);
    }

    //TODO Remove this after removing all calls to this method.
    public static Integer getLoggedInUser(HttpHeaders httpHeaders)
    {
        
        return null;
    }
}

