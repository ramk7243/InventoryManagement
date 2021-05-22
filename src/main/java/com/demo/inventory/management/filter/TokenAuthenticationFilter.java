package com.demo.inventory.management.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		log.debug("Retrieving principal from token");
		return request.getHeader("X-Token");
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return request.getHeader("X-Token");
	}

}
