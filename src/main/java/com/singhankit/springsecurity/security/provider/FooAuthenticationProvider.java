package com.singhankit.springsecurity.security.provider;

import com.singhankit.springsecurity.security.authentication.FooAuthenticaton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author _singhankit
 */
@Component
public class FooAuthenticationProvider implements AuthenticationProvider {

    @Value("${custom.authentication}")
    private String authenticationKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        if (name.equals(authenticationKey)) {
            return new FooAuthenticaton(null, null, null);
        } else {
            throw new BadCredentialsException("Invalid!!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FooAuthenticaton.class.equals(authentication);
    }
}
