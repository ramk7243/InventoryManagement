package com.demo.inventory.management.model;

import java.util.Set;

import org.springframework.security.core.userdetails.User;

import com.demo.inventory.management.security.CustomAuthority;

import lombok.Getter;

@Getter
public class TokenUserDetails extends User {

	private static final long serialVersionUID = 1L;

	private String id;

	private String token;
	
	private String email;

	public TokenUserDetails(String id, String email, String password, String token,
			Set<CustomAuthority> authorities) {
		super(email, password, true, true, true, true, authorities);
		this.id = id;
		this.token = token;
		this.email = email;

	}

}
