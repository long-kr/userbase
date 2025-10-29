package com.userbase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userbase.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);
}
