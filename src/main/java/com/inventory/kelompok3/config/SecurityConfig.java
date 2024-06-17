package com.inventory.kelompok3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
/** 
 * Class SecurityConfig
 * Konfigurasi autentikasi API dengan Basic-Auth
 * 
 * @author Muhammad Ridwan Na'im - 211011450453
 * @since 2024
*/
@Configuration
public class SecurityConfig  {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails apiUser = User.withUsername("restapi_simdc")
            .password(passwordEncoder().encode("Load1n9321!@#"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(apiUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(a ->  a.requestMatchers(
                "/**",
                "/v2/api-docs/**", 
                "/v2/api-docs.yaml", 
                "/swagger-resources/**"
            ).hasRole("ADMIN").anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthFailure();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}