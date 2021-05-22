package com.demo.inventory.management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
public class ApplicationYAML {

}
