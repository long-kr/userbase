package com.userbase.user.entity;

import com.userbase.user.utils.AuthoritiesConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = "email")
@Table(name = "userbase")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // 0 delete, 1 active, 2 draft
    @Column(nullable = false)
    private int status = 1;

    @Column(nullable = true)
    private String pic;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String role = AuthoritiesConstants.USER;
}
