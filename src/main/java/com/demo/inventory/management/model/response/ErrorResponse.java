package com.demo.inventory.management.model.response;

import com.demo.inventory.management.utils.ErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends Response
{

    private static final long serialVersionUID = 1L;

    private Integer e;

    private String m;

    public ErrorResponse(String m)
    {
        super(Boolean.FALSE);
        this.m = m;
    }

    public ErrorResponse(Integer e, String m)
    {
        super(Boolean.FALSE);
        this.e = e;
        this.m = m;
    }

    public ErrorResponse(ErrorType error)
    {
        super(Boolean.FALSE);
        this.e = error.code;
        this.m = error.message;
    }

}
