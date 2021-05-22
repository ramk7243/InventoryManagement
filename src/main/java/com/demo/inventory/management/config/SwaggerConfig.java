package com.demo.inventory.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.demo.inventory.management.controller"))
            .paths(PathSelectors.any()).build().apiInfo(metaData());
    }

    private ApiInfo metaData()
    {
        return new ApiInfoBuilder().title("Product Inventory Demo APIs")
            .description("\"REST APIs for Demo Project\"")
            .version("1.0.0")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
            .contact(new Contact("Ram Kumar", "", "ramkumar7243ec@gmail.com")).build();
    }
}
