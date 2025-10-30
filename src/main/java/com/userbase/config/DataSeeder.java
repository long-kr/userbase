package com.userbase.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.userbase.common.Status;
import com.userbase.common.UserRole;
import com.userbase.entity.User;
import com.userbase.entity.UserProfile;
import com.userbase.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// DataSeeder.java
@Configuration
@Profile({ "dev" })
@RequiredArgsConstructor
public class DataSeeder {

    private final UserRepository userRepository;

    @Bean
    CommandLineRunner seedDefaults() {
        return args -> {
            seedAdmin();
            seedRegularUser();
        };
    }

    private void seedAdmin() {
        final String adminEmail = "admin@example.com";
        userRepository.findByEmail(adminEmail).ifPresentOrElse(
                u -> {
                    /* already exists – do nothing */},
                () -> {
                    User admin = new User();
                    admin.setSupabaseUserId("supabase-admin-uuid");
                    admin.setEmail(adminEmail);
                    admin.setStatus(Status.ACTIVE);
                    admin.setTimezone("America/Chicago");

                    UserProfile p = new UserProfile();
                    p.setFullName("Admin User");
                    p.setPhoneNumber("000-000-0000");
                    p.setAvatarUrl(null);
                    admin.setProfile(p);

                    admin.setRole(UserRole.ADMIN);
                    userRepository.save(admin);
                });
    }

    private void seedRegularUser() {
        final String userEmail = "user@example.com";
        userRepository.findByEmail(userEmail).ifPresentOrElse(
                u -> {
                    /* already exists – do nothing */},
                () -> {
                    User user = new User();
                    user.setSupabaseUserId("supabase-user-uuid");
                    user.setEmail(userEmail);
                    user.setStatus(Status.ACTIVE);
                    user.setTimezone("UTC");

                    UserProfile p = new UserProfile();
                    p.setFullName("Regular User");
                    p.setPhoneNumber("111-222-3333");
                    p.setAvatarUrl(null);
                    user.setProfile(p);

                    user.setRole(UserRole.USER);
                    userRepository.save(user);
                });
    }
}
