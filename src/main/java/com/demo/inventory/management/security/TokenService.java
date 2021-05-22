package com.demo.inventory.management.security;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.inventory.management.entity.Member;
import com.demo.inventory.management.exception.TokenCreationException;
import com.demo.inventory.management.model.TokenProperties;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TokenService {
	private TokenProperties tokenProperties;
	private String issuer;
	private Algorithm algorithm;
	private JWTVerifier verifier;

	@Autowired
	public TokenService(TokenProperties tokenProperties, @Value("${spring.application.name}") String issuer)
			throws UnsupportedEncodingException {
		this.tokenProperties = tokenProperties;
		this.issuer = issuer;
		this.algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
		this.verifier = JWT.require(algorithm).acceptExpiresAt(0).build();
	}

	public DecodedJWT decode(String token) {
		return this.verifier.verify(token);
	}

	public String encode(Member member) {
		LocalDateTime now = LocalDateTime.now();
		try {
			return JWT.create().withIssuer(issuer).withSubject(member.getId())
					.withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
					.withExpiresAt(Date.from(now.plusSeconds(tokenProperties.getMaxAgeSeconds())
							.atZone(ZoneId.systemDefault()).toInstant()))
					.withClaim("email", member.getEmail())
					.sign(algorithm);
		} catch (JWTCreationException ex) {
			log.error("Cannot properly create token", ex);
			throw new TokenCreationException("Cannot properly create token", ex);
		}
	}

}
