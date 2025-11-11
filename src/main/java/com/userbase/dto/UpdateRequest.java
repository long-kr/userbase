package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.entity.User;
import com.userbase.entity.UserProfile;

import jakarta.validation.constraints.Email;

public record UpdateRequest(
                @Email String email,
                String timezone,
                UserProfile profile

) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        public static User toEntity(UpdateRequest req, User user) {
                if (req.email() != null) {
                        user.setEmail(req.email());
                }
                if (req.timezone() != null) {
                        user.setTimezone(req.timezone());
                }
                if (req.profile() != null) {
                        user.setProfile(req.profile());
                }
                return user;
        }

}