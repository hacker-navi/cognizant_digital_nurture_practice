package com.cognizant.springlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration for the spring-learn application.
 *
 * Key decisions:
 *  - /authenticate is publicly accessible (no auth required) — this is where
 *    the client sends Basic credentials to receive a JWT
 *  - All other endpoints require authentication
 *  - Session management is STATELESS (JWT-based, no HTTP session)
 *  - httpBasic() is enabled so that -u user:pwd curl flag sends Basic Auth header
 *  - CSRF is disabled (standard for stateless REST APIs)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain.
     *
     * Permit /authenticate without credentials so the client can
     * obtain a JWT token. All other endpoints require valid authentication.
     *
     * @param http HttpSecurity builder
     * @return configured SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF — not needed for stateless REST APIs
            .csrf(csrf -> csrf.disable())

            // Define authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoint — client calls this to get JWT
                .requestMatchers("/authenticate").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
            )

            // Enable HTTP Basic authentication (used by curl -u user:pwd)
            .httpBasic(httpBasic -> {})

            // Stateless sessions — Spring Security won't create HttpSession
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}
