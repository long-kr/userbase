package com.userbase.entity;

import java.util.Set;

import com.userbase.common.UserRole;
import com.userbase.common.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = "email")
@Table(name = "userbase")
public class User extends BaseEntity {
    @Version
    private Long version;

    @Column(nullable = false, unique = true)
    private String supabaseUserId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Status status;

    private String timezone = "UTC";

    @Column(nullable = false)
    private UserRole role;

    @Embedded
    private UserProfile profile;
}
