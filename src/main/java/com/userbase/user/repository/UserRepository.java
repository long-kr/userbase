package com.userbase.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userbase.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(long id);

    Optional<UserEntity> findByEmail(String email);

}
