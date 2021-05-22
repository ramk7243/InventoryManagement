package com.demo.inventory.management.config;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.io.Resources;


@Configuration
public class ProductInventoryConfig {
	
    @Autowired
    ApplicationYAML applicationYAML;
    
    // Loading Files while Startup spring boot server to avoid read everytime when request comes.
    @Bean
    public String productFormSchema()
    {
        String text = null;
        URL url = Resources.getResource("schema/request-schema/product-form-request.jsd");
        try {
            text = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
