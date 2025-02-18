package com.bugblogs.blog.entity;

import com.bugblogs.blog.utils.UserRole;

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
@Table(name = "bbuser")
public class BBUser extends BBEntity {
    @Column(nullable = false, length = 50, name = "user_name")
    private String username;

    @Column(nullable = false, unique = true, name = "user_email")
    private String email;

    @Column(nullable = false, name = "user_password")
    private String password;

    // 0 delete, 1 active, 2 draft
    @Column(name = "user_status", nullable = false)
    private int status = 1;

    @Column(name = "user_pic")
    private String pic;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;
}
