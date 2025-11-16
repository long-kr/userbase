package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.userbase.entity.Status;
import com.userbase.entity.User;
import com.userbase.entity.UserRole;
import com.userbase.entity.UserProfile;

public record UserDto(
        Long id,
        String supabaseUserId,
        String email,
        Status status,
        String timezone,
        UserRole role,
        UserProfile profile,
        List<ApplicationDto> applications)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getSupabaseUserId(),
                user.getEmail(),
                user.getStatus(),
                user.getTimezone(),
                user.getRole(),
                user.getProfile(),
                null);
    }

    public static UserDto toDtoWithoutApplications(User user, List<ApplicationDto> applications) {
        return new UserDto(
                user.getId(),
                user.getSupabaseUserId(),
                user.getEmail(),
                user.getStatus(),
                user.getTimezone(),
                user.getRole(),
                user.getProfile(),
                applications);
    }

}
