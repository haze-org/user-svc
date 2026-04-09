package com.haze.user_svc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // this request is protected
                        .requestMatchers("/follow/**").authenticated()
                        .requestMatchers("/profile/**").authenticated()
                        // any other request is permitted
                        .anyRequest().permitAll())
                /*
                 user-svc will act as a resource server to check jwt token sent in the headers against the issuer
                 uri from keycloak in the application.yaml configurations
                 */
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}