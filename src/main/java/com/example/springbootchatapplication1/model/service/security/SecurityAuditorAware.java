package com.example.springbootchatapplication1.model.service.security;

import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserEntity)) {
            return Optional.empty();
        }
        return Optional.of(((UserEntity) authentication.getPrincipal()).getId());
    }
}
