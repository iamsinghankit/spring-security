package com.singhankit.springsecurity.security.provider;

import com.singhankit.springsecurity.security.authentication.UsernamePasswordAuthentication;
import com.singhankit.springsecurity.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author _singhankit
 */
@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private  JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthentication(username, password, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Invalid user!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
