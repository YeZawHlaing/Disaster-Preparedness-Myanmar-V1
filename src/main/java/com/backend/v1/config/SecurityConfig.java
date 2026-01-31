package com.backend.v1.config;

import com.backend.v1.authExceptionHandler.CustomAuthenticationExceptionHandler;
import com.backend.v1.authProviders.CustomUsernamePasswordAuthenticationProvider;
import com.backend.v1.common.middleware.JwtValidationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        CustomUsernamePasswordAuthenticationProvider authenticationProvider = new CustomUsernamePasswordAuthenticationProvider(userDetailsService, passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;

    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http){

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(smc -> {
            smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class);

//        http.requiresChannel(config -> {
//            config.anyRequest().requiresSecure();
//        });

        http.exceptionHandling(config -> {
            config.authenticationEntryPoint(new CustomAuthenticationExceptionHandler());
        });


        http.authorizeHttpRequests(authorize -> {
//            authorize.requestMatchers("/backend/users/**").permitAll();
            authorize.requestMatchers("/hello").hasRole("admin");
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
                    cors.setExposedHeaders(Arrays.asList("Authorization"));
                    cors.setAllowCredentials(true);
                    cors.setMaxAge(3600L);
                    return cors;
                }
            });
        });

        return http.build();
    }

}
