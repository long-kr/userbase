package com.userbase.user.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.user.utils.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String username;
    private String email;
    private String password;
    private int status;
    private String pic;
    private UserRole role;

}
