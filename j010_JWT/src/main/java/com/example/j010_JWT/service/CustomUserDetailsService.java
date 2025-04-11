package com.example.j010_JWT.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//used to set username and password for login // i'm not using DB so I hardType U&P;
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired //autowired form MySecurityConfig to encode password;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException(username + " <- Not found");
        }

        //User is a class provide by the Spring Security which takes username, encrypted password and array of security rules or ROLEs;
        return new User("admin", passwordEncoder.encode("password"), new ArrayList<>());
    }
}
