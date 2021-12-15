package com.singhankit.springsecurity.config;

import com.singhankit.springsecurity.security.CsrfTokenLoggerFilter;
import com.singhankit.springsecurity.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * @author _singhankit
 */
@Configuration
public class MainConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);//for login form
        http.csrf().disable();// GENERALLY DON'T do this :)
        http.csrf(c -> {
            c.ignoringAntMatchers("/csrfdiabled/**");
            c.csrfTokenRepository(new CustomCsrfTokenRepository());
        });

        http.addFilterAfter(new CsrfTokenLoggerFilter(),
                CsrfFilter.class
        );
    }
}
