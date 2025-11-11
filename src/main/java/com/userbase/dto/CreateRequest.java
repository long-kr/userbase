package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.entity.Status;
import com.userbase.entity.User;
import com.userbase.entity.UserProfile;
import com.userbase.entity.UserRole;
import com.userbase.validator.TimezoneValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRequest(
                @NotBlank String supabaseUserId,
                @NotNull @Email String email,
                @TimezoneValidator @Size(min = 3, max = 50) String timezone,
                UserProfile profile) implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        public static User toEntity(CreateRequest req) {
                return User.builder()
                                .supabaseUserId(req.supabaseUserId())
                                .email(req.email())
                                .timezone(req.timezone() != null ? req.timezone() : "UTC")
                                .role(UserRole.USER)
                                .status(Status.PENDING)
                                .profile(req.profile())
                                .build();
        }

}
