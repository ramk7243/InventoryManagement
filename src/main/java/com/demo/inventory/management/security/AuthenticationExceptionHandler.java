package com.demo.inventory.management.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.model.response.ErrorResponse;
import com.demo.inventory.management.model.response.Response;
import com.demo.inventory.management.utils.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		Response res = new ErrorResponse(ErrorType.UNAUTHORIZED);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = response.getWriter();
			writer.write(mapper.writeValueAsString(res));
			writer.flush();
			writer.close();
		} catch (IOException ie) {
			log.error(ie.getMessage(), ie);
		}

	}
}