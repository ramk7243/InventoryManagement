package com.demo.inventory.management.model.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Boolean s;

    private Object d;

    protected Response(Boolean s)
    {
        this.s = s;
    }

    public Response(Object d)
    {
        this.s = Boolean.TRUE;
        this.d = d;
    }

}