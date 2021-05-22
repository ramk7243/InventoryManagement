package com.demo.inventory.management.exception;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.utils.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Scope("prototype")
@Component
@Getter
@Setter
@AllArgsConstructor
public class InventoryException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private HttpStatus httpStatus;

    private ErrorType error;

}
