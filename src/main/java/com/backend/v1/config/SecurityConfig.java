package com.backend.v1.config;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http){

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(smc -> {
            smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

//        http.requiresChannel(config -> {
//            config.anyRequest().requiresSecure();
//        });

        http.authorizeHttpRequests(authorize -> {
            authorize.anyRequest().authenticated();
        });

        http.cors(cc -> {
            cc.configurationSource(new CorsConfigurationSource() {
                @Override
                public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Collections.singletonList("*"));
                    cors.setAllowedHeaders(Collections.singletonList("*"));
                    cors.setAllowedMethods(Collections.singletonList("*"));
                    cors.setAllowCredentials(true);
                    cors.setMaxAge(3600L);
                    return cors;
                }
            });
        });

        return http.build();
    }

}
