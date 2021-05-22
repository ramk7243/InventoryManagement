package com.demo.inventory.management.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenProperties
{

    private long maxAgeSeconds;

    private String secret;

    public TokenProperties(long maxAgeSeconds, String secret)
    {
        this.maxAgeSeconds = maxAgeSeconds;
        this.secret = secret;
    }

    public TokenProperties()
    {
    }
}