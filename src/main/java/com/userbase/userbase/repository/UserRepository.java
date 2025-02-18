package com.bugblogs.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugblogs.blog.entity.BBUser;

public interface UserRepository extends JpaRepository<BBUser, Long> {
    Optional<BBUser> findById(long id);

    Optional<BBUser> findByEmail(String email);

}
