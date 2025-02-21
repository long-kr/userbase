package com.userbase.user.dto;

import java.io.Serial;
import java.io.Serializable;

import com.userbase.user.utils.AuthoritiesConstants;
import com.userbase.user.utils.EnumValidator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Min(0)
    @Max(3)
    private int status;

    private String pic;

    @NotNull
    @EnumValidator(acceptedValues = { AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER })
    private String role;

}
