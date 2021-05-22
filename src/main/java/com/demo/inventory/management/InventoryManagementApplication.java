package com.demo.inventory.management;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.inventory.management.model.TokenProperties;

@SpringBootApplication
@EnableJpaAuditing
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "security.token")
	public TokenProperties tokenProperties() {
		return new TokenProperties();
	}

	@Bean
	public PasswordEncoder passwordEncoder(@Value("${security.password.strength:10}") int strength) {
		return new BCryptPasswordEncoder(strength);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
