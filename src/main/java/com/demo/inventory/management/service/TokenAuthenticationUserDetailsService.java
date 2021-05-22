package com.demo.inventory.management.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.inventory.management.exception.CustomAuthenticationException;
import com.demo.inventory.management.model.TokenUserDetails;
import com.demo.inventory.management.security.CustomAuthority;
import com.demo.inventory.management.security.TokenService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TokenAuthenticationUserDetailsService
    implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authentication)
        throws UsernameNotFoundException
    {
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof String
            && authentication.getCredentials() instanceof String) {
            DecodedJWT token;
            try {
                token = tokenService.decode((String) authentication.getPrincipal());
                String memberId = token.getSubject();
                Set<CustomAuthority> authorities = memberService.getAuthorities(memberId);
                return new TokenUserDetails(memberId, token.getClaim("email").asString(),
                    (String) authentication.getCredentials(), token.getToken()
                    , authorities);
            } catch (TokenExpiredException ex) {
                throw new CustomAuthenticationException(ex.getMessage(), ex.getCause());
            }
        } else {
            log.error("Could not retrieve user details for '" + authentication.getPrincipal() + "'");
            throw new UsernameNotFoundException(
                "Could not retrieve user details for '" + authentication.getPrincipal() + "'");
        }
    }

}
