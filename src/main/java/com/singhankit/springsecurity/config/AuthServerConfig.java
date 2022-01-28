package com.singhankit.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
    authorization_code  /  pkce
    password -----> deprecated
    client_credentials
    refresh_token
    implicit -----> deprected

    curl --location --request POST 'http://localhost:8080/oauth/token?grant_type=password&username=john&password=12345' \
--header 'Authorization: Basic Y2xpZW50MTpzZWNyZXQx'

    localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read

    curl --location --request POST 'http://localhost:8080/oauth/token?grant_type=authorization_code&code=Nim2ov' \
--header 'Authorization: Basic Y2xpZW50MjpzZWNyZXQy'
     */


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //opaque token contains no information unlike jwt
        clients.inMemory()
               .withClient("client1")
               .secret("secret1")
               .scopes("read")
               .authorizedGrantTypes("password")
               .and()
               .withClient("client2")
               .secret("secret2")
               .scopes("read")
               .authorizedGrantTypes("authorization_code")
               .redirectUris("http://localhost:9090");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }
}