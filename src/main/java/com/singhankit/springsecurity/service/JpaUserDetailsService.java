package com.singhankit.springsecurity.service;

import com.singhankit.springsecurity.entity.User;
import com.singhankit.springsecurity.repository.UserRepository;
import com.singhankit.springsecurity.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var o = userRepository.findUserByUsername(username);
        User u = o.orElseThrow(() -> new UsernameNotFoundException(":("));
        return new SecurityUser(u);
    }
}