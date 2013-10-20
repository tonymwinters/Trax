package com.trax.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userService;

    ShaPasswordEncoder sha = new ShaPasswordEncoder(512);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = sha.encodePassword((String) authentication.getCredentials(), null);

        User user = (User) userService.loadUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}