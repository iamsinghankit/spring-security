package com.singhankit.springsecurity.repo;

import com.singhankit.springsecurity.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author _singhankit
 */
public interface OtpRepostitory extends JpaRepository<Otp, Integer> {
    Optional<Otp> findOtpByUsername(String username);
}
