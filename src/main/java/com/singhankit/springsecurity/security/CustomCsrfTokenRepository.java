package com.singhankit.springsecurity.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author _singhankit
 */
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        CsrfToken t = new DefaultCsrfToken(
                "X-CSRF-TOKEN",
                "_csrf",
                "123456789");
        return t;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        return null;
    }
}