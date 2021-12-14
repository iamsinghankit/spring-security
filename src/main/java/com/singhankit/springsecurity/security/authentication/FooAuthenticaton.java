package com.singhankit.springsecurity.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author _singhankit
 */
public class FooAuthenticaton extends UsernamePasswordAuthenticationToken {
    public FooAuthenticaton(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public FooAuthenticaton(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
