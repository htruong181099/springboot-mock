package com.hoangtm14.spring.config;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.security.SecurityFilter;
import com.hoangtm14.spring.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] whiteList = new String[]{
                "/auth/**",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/v2/api-doc"
        };

        String[] adminRoutesList = new String[]{
                "/**"
        };
        String[] userRoutesList = new String[]{};
        String[] sellerRoutesList = new String[]{
                "/product/revenue",
                "/product/**/revenue",
        };

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(whiteList)
                .permitAll()
                .antMatchers(adminRoutesList)
                .hasAuthority(Constants.Roles.ADMIN)
//                .antMatchers(userRoutesList)
//                .hasAuthority(Constants.Roles.USER)
                .antMatchers(sellerRoutesList)
                .hasAuthority(Constants.Roles.SELLER)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
