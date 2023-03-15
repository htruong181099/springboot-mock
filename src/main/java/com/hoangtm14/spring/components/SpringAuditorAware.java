package com.hoangtm14.spring.components;

import com.hoangtm14.spring.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringAuditorAware implements AuditorAware<String> {

    public SpringAuditorAware() {
    }

    public Optional<String> getCurrentAuditor() {
        return getCurrentUser();
    }

    public Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("anonymous");
        }
        return Optional.of(SecurityUtil.getCurrentUserId().toString());
    }
}
