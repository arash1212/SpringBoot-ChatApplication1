package com.example.springbootchatapplication1.config;

import com.example.springbootchatapplication1.model.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> {
            csrf.disable();
        });
        http.cors((cors) -> {
            cors.disable();
        });

        http.authorizeHttpRequests((request) -> {
            request.requestMatchers("/styles/**", "/js/**", "/", "/index",
                            "/pub/**", "/swagger-ui/**", "/api-docs", "/api-docs/**")
                    .permitAll().anyRequest().authenticated();
        });

        http.formLogin((form) -> {
            form.loginPage("/pub/user/login").successForwardUrl("/").permitAll();
        });


        http.userDetailsService(this.userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
