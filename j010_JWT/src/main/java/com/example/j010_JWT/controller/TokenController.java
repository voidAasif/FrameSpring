package com.example.j010_JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.j010_JWT.helper.JwtUtil;
import com.example.j010_JWT.model.JwtRequest;
import com.example.j010_JWT.service.CustomUserDetailsService;

import org.springframework.security.core.AuthenticationException;

@RestController
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired //autowired form spring security config;
    private DaoAuthenticationProvider daoAuthenticationProvider;
    
    @PostMapping("/token") //this endpoint is used to generate token;
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){

        try { 
            daoAuthenticationProvider.authenticate( //authenticate the user if username and password not match then throws AuthenticationException;
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
    
            // authentication is already verified — no need to check isAuthenticated again
            String token = jwtUtil.generateToken(
                customUserDetailsService.loadUserByUsername(jwtRequest.getUsername())
            );
    
            return ResponseEntity.ok(token);
    
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
}
