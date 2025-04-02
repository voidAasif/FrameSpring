package com.example.j010_JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.j010_JWT.helper.JwtUtil;
import com.example.j010_JWT.model.JwtRequest;
import com.example.j010_JWT.service.CustomUserDetailsService;

@RestController
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @PostMapping("/token") //this endpoint is used to generate token;
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){

        // Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        //generate token for first request;
        String generateToken = jwtUtil.generateToken(customUserDetailsService.loadUserByUsername(jwtRequest.getUsername()));

        return ResponseEntity.ok(generateToken);
    }
    
}
