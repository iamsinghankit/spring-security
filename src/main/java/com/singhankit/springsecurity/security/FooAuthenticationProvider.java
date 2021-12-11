package com.singhankit.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author _singhankit
 */
@Component
public class FooAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //type of Authentication is determined by supports(Class<?> authType)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //here you implement the authentication login, if the request is authentic you should return a fully
        //authenticated Authentication instance.

        // if the request is not  authenticated you should throw AuthenticationException.

        // if the authentication is not supported by this AP then return null.
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            }
        }

        throw  new BadCredentialsException("Invalid credentials!");
    }

    //Called by authentication manager before authenticate().
    @Override
    public boolean supports(Class<?> authType) {//type of authentication, which used this disclose whether this
        // authetication provider knows or not how to authenticate this authentication
        return UsernamePasswordAuthenticationToken.class.equals(authType);
        //here it means authenticationManager will  call my AP only if it did receive from the filter the type of
        // authentication called UsernamePasswordAuthenticationToken, it is also the default used by
        // BasicAuthenticationFilter
    }
}
