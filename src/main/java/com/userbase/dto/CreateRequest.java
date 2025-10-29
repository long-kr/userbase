package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.entity.UserProfile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRequest(
                @NotBlank String supbaseUserId,
                @NotNull @Email String email,
                @Size(min = 3, max = 50) String timezone,
                @Valid UserProfile profile) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

}
