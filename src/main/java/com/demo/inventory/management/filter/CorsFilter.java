package com.demo.inventory.management.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.demo.inventory.management.utils.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader(Constants.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.setHeader(Constants.ACCESS_CONTROL_ALLOW_CREDENTIALS, Constants.TRUE);
		response.setHeader(Constants.ACCESS_CONTROL_ALLOW_METHODS, Constants.ACCESS_CONTROL_ALLOW_METHODS_LIST);
		response.setHeader(Constants.ACCESS_CONTROL_MAX_AGE, Constants.MAX_AGE_VALUE);
		response.setHeader(Constants.ACCESS_CONTROL_ALLOW_HEADERS, Constants.ACCESS_CONTROL_ALLOW_HEADERS_LIST);

		if (Constants.OPTIONS.equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig filterConfig) {
		// not needed
	}

	public void destroy() {
		// not needed
	}

}