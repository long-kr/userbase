package com.userbase.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.userbase.entity.Status;
import com.userbase.entity.UserRole;
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

                    userRepository.save(User.builder()
                            .supabaseUserId("supabase-admin-uuid")
                            .email("adminEmail")
                            .status(Status.ACTIVE)
                            .timezone("America/Chicago")
                            .role(UserRole.ADMIN)
                            .profile(
                                    UserProfile.builder()
                                            .fullName("Admin User")
                                            .phoneNumber("000-000-0000")
                                            .avatarUrl("https://example.com/admin-avatar.png")
                                            .build())
                            .build());
                });
    }

    private void seedRegularUser() {
        final String userEmail1 = "user@example.com";
        final String userEmail2 = "user2@example.com";

        userRepository.findByEmail(userEmail1).ifPresentOrElse(
                u -> {
                    /* already exists – do nothing */},
                () -> {

                    userRepository.save(User.builder()
                            .supabaseUserId("supabase-user-uuid-1")
                            .email(userEmail1)
                            .status(Status.ACTIVE)
                            .timezone("UTC")
                            .role(UserRole.USER)
                            .profile(
                                    UserProfile.builder()
                                            .fullName("Regular User")
                                            .phoneNumber("111-222-3333")
                                            .avatarUrl(null)
                                            .build())
                            .build());
                });

        userRepository.findByEmail(userEmail2).ifPresentOrElse(
                u -> {
                    /* already exists – do nothing */},
                () -> {

                    userRepository.save(User.builder()
                            .supabaseUserId("supabase-user-uuid-2")
                            .email(userEmail2)
                            .status(Status.ACTIVE)
                            .timezone("UTC")
                            .role(UserRole.USER)
                            .profile(
                                    UserProfile.builder()
                                            .fullName("Second User")
                                            .phoneNumber("444-555-6666")
                                            .avatarUrl(null)
                                            .build())
                            .build());
                });
    }
}
