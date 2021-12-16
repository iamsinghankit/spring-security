package com.singhankit.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * @author _singhankit
 */
@Configuration
public class MainConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
        //don't do customization here only, create a different class for all the customization
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration cors = new CorsConfiguration();
                cors.setAllowedOrigins(List.of("*"));
                cors.setAllowedMethods(List.of("GET","POST")); // value should be taken from properties files for
                // specific environment
                return cors;
            };
            c.configurationSource(source);
        });

    }
}
