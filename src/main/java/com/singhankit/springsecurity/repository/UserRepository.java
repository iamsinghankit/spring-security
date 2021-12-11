package com.singhankit.springsecurity.repository;

import com.singhankit.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author _singhankit
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
