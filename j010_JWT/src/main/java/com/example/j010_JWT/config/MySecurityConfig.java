package com.example.j010_JWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.j010_JWT.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity //enable security features, This tells Spring Boot that you want to take control of your application's security configuration;
public class MySecurityConfig {

    @Bean
    public CustomUserDetailsService customUserDetailsService(){ //my user details service;
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //to encode password, used in CustomUserDetailsService;
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ //set CustomUserDetailsService and BCryptPasswordEncoder into DaoAuthenticationProvider;
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(this.customUserDetailsService());
        provider.setPasswordEncoder(this.passwordEncoder());

        return provider;
    }

    @Bean // Defines security rules and configurations for handling requests;
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //view access configuration;
        http
                .csrf(csrf -> csrf.disable()) //disable csrf;
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login").permitAll().anyRequest().authenticated()) //block all URL except "/login"
                .formLogin(form -> form //config for loginForm;
                        .defaultSuccessUrl("/welcome", true) //if login success then goto here;
                        .permitAll()); //Allows all users to access the login page;

        return http.build();
    }

    
}
