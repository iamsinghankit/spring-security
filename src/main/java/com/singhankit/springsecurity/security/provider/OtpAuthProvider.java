package com.singhankit.springsecurity.security.provider;

import com.singhankit.springsecurity.repo.OtpRepostitory;
import com.singhankit.springsecurity.security.authentication.OtpAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author _singhankit
 */
@Component
public class OtpAuthProvider implements AuthenticationProvider {

    @Autowired
    private  OtpRepostitory otpRepostitory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = String.valueOf(authentication.getCredentials());

        return otpRepostitory.findOtpByUsername(username)
                             .map(e -> new OtpAuthentication(username, otp, List.of(() -> "read")))
                             .orElseThrow(() -> new BadCredentialsException("Invalid otp!!"));

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
