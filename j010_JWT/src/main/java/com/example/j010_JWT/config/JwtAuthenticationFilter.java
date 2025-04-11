package com.example.j010_JWT.config;

import com.example.j010_JWT.helper.JwtUtil;
import com.example.j010_JWT.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter { //has doFilterInternal method

    // @Autowired // cause circular dependency;
    // private CustomUserDetailsService customUserDetailsService;

    //use constructor injection instead of Autowired;
    private final CustomUserDetailsService customUserDetailsService; //used to load user by username;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Autowired
    private JwtUtil jwtUtil; //used for token opr;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); //get Authorization header;
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) { //check token
            jwtToken = authHeader.substring(7); //extract token;
            try {
                username = jwtUtil.extractUsername(jwtToken); //get username form token;
            } catch (Exception e) {
                System.out.println("Invalid token: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { //check username if user found && check user is not authenticated yet;
            //UserDetails has all attributes of user;
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username); //load user by username;

            if (jwtUtil.validateToken(jwtToken, userDetails)) { //validate token by token and userDetails;
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()); //authenticate user by userDetails, credentials and role;

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //build request;
                SecurityContextHolder.getContext().setAuthentication(authToken); //return authenticated token;
            }
        }

        filterChain.doFilter(request, response); //now add request and response into security chain;
    }
}
