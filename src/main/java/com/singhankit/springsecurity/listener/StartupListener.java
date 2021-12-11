package com.singhankit.springsecurity.listener;

import com.singhankit.springsecurity.entity.User;
import com.singhankit.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author _singhankit
 */
@Component
@RequiredArgsConstructor
public class StartupListener {

    private final UserRepository userRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void insertUser() {
        User user = new User();
        user.setUsername("toyko");
        user.setPassword("toyko");
        userRepository.save(user);
    }
}
