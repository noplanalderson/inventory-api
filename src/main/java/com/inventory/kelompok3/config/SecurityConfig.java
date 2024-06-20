package com.inventory.kelompok3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
/** 
 * Class SecurityConfig
 * Konfigurasi autentikasi API dengan Basic-Auth
 * 
 * @author Muhammad Ridwan Na'im - 211011450453
 * @since 2024
*/
@Configuration
public class SecurityConfig  {

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;
    
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
                "/v2/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui/index.html",
                "/swagger-ui/",
                "/swagger-ui/index.html#/",
                "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config",
                "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/"
            ).permitAll()
            .requestMatchers(
                "/users/**",
                "/devices/**",
                "/manufactures/**",
                "/device-models/**",
                "/groups/**"
            ).hasRole("ADMIN").anyRequest().authenticated())
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customAuthEntryPoint));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}