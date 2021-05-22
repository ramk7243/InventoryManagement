package com.demo.inventory.management.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.demo.inventory.management.model.TokenUserDetails;

@Configuration
public class AuditingConfig
{
    @Bean
    public AuditorAware<String> auditorProvider()
    {
        return new SpringSecurityAuditAwareImpl();
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<String>
{

    @Override
    public Optional<String> getCurrentAuditor()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        TokenUserDetails principal = (TokenUserDetails) authentication.getPrincipal();

        return Optional.ofNullable(principal.getId());
    }
}
