package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.common.Status;
import com.userbase.entity.UserProfile;

public record UserDto(Long id,
        String email, Status status, String timezone, UserProfile profile)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
