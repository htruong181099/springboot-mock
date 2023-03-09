package com.hoangtm14.spring.components;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringAuditorAware implements AuditorAware<String> {

    public SpringAuditorAware() {
    }

    public Optional<String> getCurrentAuditor() {
        return Optional.of("HoangTest");
    }
}
