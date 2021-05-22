package com.demo.inventory.management.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import com.demo.inventory.management.filter.TokenAuthenticationFilter;
import com.demo.inventory.management.security.AuthenticationExceptionHandler;
import com.demo.inventory.management.security.CustomDaoAuthenticationProvider;
import com.demo.inventory.management.security.LegacyPasswordEncoder;
import com.demo.inventory.management.service.CustomUserDetailsService;
import com.demo.inventory.management.service.TokenAuthenticationUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService service;
    
	@Autowired
	private LegacyPasswordEncoder passwordEncoder;

	@Configuration
	@Order(1)
	public class BasicAuthConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authProvider());
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.antMatcher("/auth/signin").authorizeRequests().anyRequest().authenticated().and().httpBasic()
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

		@Bean
		public CustomDaoAuthenticationProvider authProvider() {
			CustomDaoAuthenticationProvider provider = new CustomDaoAuthenticationProvider();
			provider.setPasswordEncoder(passwordEncoder);
			provider.setUserDetailsService(service);
			return provider;
		}
	}

	@Configuration
	@Order(2)
	public class TokenAuthConfig extends WebSecurityConfigurerAdapter {
		private TokenAuthenticationUserDetailsService preAuthService;

		@Autowired
		public TokenAuthConfig(TokenAuthenticationUserDetailsService preAuthService) {
			this.preAuthService = preAuthService;
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.antMatcher("/api/**").addFilterAfter(authFilter(), RequestHeaderAuthenticationFilter.class)
					.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
					.authenticationEntryPoint(new AuthenticationExceptionHandler()).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().formLogin()
					.disable();
		}

		@Bean
		public TokenAuthenticationFilter authFilter() {
			TokenAuthenticationFilter authFilter = new TokenAuthenticationFilter();
			authFilter.setAuthenticationManager(authenticationManager());
			return authFilter;
		}

		@Override
		protected AuthenticationManager authenticationManager() {
			return new ProviderManager(Collections.singletonList(preAuthProvider()));
		}

		@Bean
		public AuthenticationProvider preAuthProvider() {
			PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
			provider.setPreAuthenticatedUserDetailsService(preAuthService);
			return provider;
		}

	}

}
