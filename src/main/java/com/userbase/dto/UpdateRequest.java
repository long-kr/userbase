package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.entity.UserProfile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record UpdateRequest(
                @Email String email,
                String timezone,
                @Valid UserProfile profile

) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

}