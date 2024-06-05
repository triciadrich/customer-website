package com.customerwebsite.customer.website.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http//most restricted at top
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/webjars/**", "/css/**", "/login/**", "/images/**", "/", "/register","/batch/job","/job")
                        .permitAll()
                        .requestMatchers("/customer-view")
                        .hasRole("USER_ROLE")
                        .anyRequest()
                        .hasRole("ADMIN_ROLE"))
                .formLogin(Customizer.withDefaults());





    return http.build();

}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}