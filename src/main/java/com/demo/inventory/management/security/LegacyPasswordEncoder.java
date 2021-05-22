package com.demo.inventory.management.security;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.model.TokenUserDetails;

@Component
public class LegacyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	public boolean matches(TokenUserDetails userDetails, CharSequence rawPassword, String encodedPassword) {
		Sha256Hash sha256Hash = new Sha256Hash(rawPassword, (new SimpleByteSource("sailfin_salt_value_" +

				userDetails.getEmail())).getBytes());
		String passHex = sha256Hash.toHex();
		return passHex.equals(encodedPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return false;
	}

}
