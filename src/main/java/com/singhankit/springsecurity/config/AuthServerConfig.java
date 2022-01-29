package com.singhankit.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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


curl --location --request POST 'http://localhost:8080/oauth/token?grant_type=client_credentials&scope=read' \
--header 'Authorization: Basic Y2xpZW50MzpzZWNyZXQz'

curl --location --request POST 'http://localhost:8080/oauth/token?grant_type=refresh_token&token=90a053de-dea9-40c4-b68c-2648993a1b11&scope=read' \
--header 'Authorization: Basic Y2xpZW50MTpzZWNyZXQx'
     */


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //opaque token contains no information unlike jwt
        clients.inMemory()
               .withClient("client1")
               .secret("secret1")
               .scopes("read")
               .authorizedGrantTypes("password", "refresh_token")
               .and()

               .withClient("client2")
               .secret("secret2")
               .scopes("read")
               .authorizedGrantTypes("authorization_code", "refresh_token")
               .redirectUris("http://localhost:9090")
               .and()

               .withClient("client3")
               .secret("secret3")
               .scopes("read")
               .authorizedGrantTypes("client_credentials");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(converter());
    }

    @Bean
    public JwtAccessTokenConverter converter() {
        return new JwtAccessTokenConverter();
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(converter());
    }


}