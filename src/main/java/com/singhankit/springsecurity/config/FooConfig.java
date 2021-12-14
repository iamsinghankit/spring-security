package com.singhankit.springsecurity.config;

import com.singhankit.springsecurity.security.filter.TokenAuthFilter;
import com.singhankit.springsecurity.security.filter.UsernamePasswordAuthFilter;
import com.singhankit.springsecurity.security.provider.OtpAuthProvider;
import com.singhankit.springsecurity.security.provider.TokenAuthProvider;
import com.singhankit.springsecurity.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author _singhankit
 */
@Configuration
public class FooConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    private  UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    @Lazy
    @Autowired
    private  OtpAuthProvider otpAuthProvider;

    @Autowired
    @Lazy
    private TokenAuthProvider tokenAuthProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter(), BasicAuthenticationFilter.class).addFilterAfter(tokenAuthFilter(),
                BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
            .authenticationProvider(otpAuthProvider)
                .authenticationProvider(tokenAuthProvider);
    }
    @Bean
    public TokenAuthFilter tokenAuthFilter() {
        return new TokenAuthFilter();
    }

    @Bean
    public UsernamePasswordAuthFilter usernamePasswordAuthFilter() {
        return new UsernamePasswordAuthFilter();
    }

}
