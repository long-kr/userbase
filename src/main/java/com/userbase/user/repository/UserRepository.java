package com.userbase.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userbase.user.entity.BBUser;

public interface UserRepository extends JpaRepository<BBUser, Long> {
    Optional<BBUser> findById(long id);

    Optional<BBUser> findByEmail(String email);

}
