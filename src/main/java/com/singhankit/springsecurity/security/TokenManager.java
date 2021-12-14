package com.singhankit.springsecurity.security;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author _singhankit
 */
@Component
public class TokenManager {
    private final ConcurrentHashMap<String, String> tokens = new ConcurrentHashMap<>();

    public void add(String username, String token) {
        tokens.put(username, token);
    }

    public boolean contains(String token) {
        return tokens.contains(token);
    }

}
