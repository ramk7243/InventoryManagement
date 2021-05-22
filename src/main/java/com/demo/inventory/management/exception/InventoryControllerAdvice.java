package com.demo.inventory.management.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.demo.inventory.management.model.response.ErrorResponse;
import com.demo.inventory.management.model.response.Response;
import com.demo.inventory.management.utils.Constants;
import com.demo.inventory.management.utils.ErrorType;
import com.demo.inventory.management.utils.InventoryUtils;



@Configuration
@ControllerAdvice
public class InventoryControllerAdvice {

    /*
     * This Exception method is used for throw customised exception. Anywhere in the project, if you want to throw any
     * exception than throw like this. throw new InventoryException(ERROR,STATUS_CODE,SOURCE,TIMESTAMP); it will be
     * handled here and it will generate proper error response.
     */
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<Response> inventoryException(final InventoryException inventoryException)
    {
        ErrorResponse errorResponse =
            (ErrorResponse) InventoryUtils.generateErrorResponse(inventoryException.getErrorMessage(),
            		inventoryException.getHttpStatus(), ErrorType.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse,inventoryException.getHttpStatus());
        
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> methodNotAllowedException()
    {
        return new ResponseEntity<>(InventoryUtils.generateErrorResponse(Constants.METHOD_NOT_ALLOWED,
            HttpStatus.METHOD_NOT_ALLOWED, ErrorType.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> methodNotFound(NoHandlerFoundException e)
    {
        return new ResponseEntity<>(
        		InventoryUtils.generateErrorResponse(Constants.NOT_FOUND, HttpStatus.NOT_FOUND, ErrorType.METHOD_NOT_FOUND),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> entityNotFound(EntityNotFoundException e)
    {
        return new ResponseEntity<>(InventoryUtils.generateErrorResponse(ErrorType.ENTITY_NOT_FOUND),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> messageNotReadableException(HttpMessageNotReadableException e)
    {
        return new ResponseEntity<>(
        		InventoryUtils.generateErrorResponse((e.getMessage()), HttpStatus.BAD_REQUEST, ErrorType.BAD_REQUEST),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException e)
    {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return new ResponseEntity<>(InventoryUtils.generateErrorResponse(ErrorType.BAD_REQUEST, errors.toString()),
            HttpStatus.BAD_REQUEST);
    }

    /*
     * If Exception are not handled in above any of the exception and it will get handaled here in Parent of all
     * Exception.Claas
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(final Exception e)
    {

        return new ResponseEntity<>(InventoryUtils.generateErrorResponse(e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
