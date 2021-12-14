package com.singhankit.springsecurity.security.filter;

import com.singhankit.springsecurity.entity.Otp;
import com.singhankit.springsecurity.repo.OtpRepostitory;
import com.singhankit.springsecurity.security.TokenManager;
import com.singhankit.springsecurity.security.authentication.OtpAuthentication;
import com.singhankit.springsecurity.security.authentication.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author _singhankit
 */
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OtpRepostitory otpRepostitory;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //step 1: username & password
        //step 2: username & otp

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (otp == null) {
            //step 1
            doAuthenticate(new UsernamePasswordAuthentication(username, password));
            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(String.valueOf(new Random().nextInt(9999) + 1000));
            otpRepostitory.save(otpEntity);
        } else {
            //step 2
            doAuthenticate(new OtpAuthentication(username, otp));
            String token = UUID.randomUUID().toString();
            response.setHeader("Authorization", token);
            tokenManager.add(username, token);
        }
    }


    private Authentication doAuthenticate(Authentication authentication) {
        return authenticationManager.authenticate(authentication);
    }
}
