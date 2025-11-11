package com.userbase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = "email")
// "user" is a reserved keyword in some SQL databases
@Table(name = "user_service")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String supabaseUserId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String timezone;

    @Column(nullable = false)
    private UserRole role;

    @Embedded
    private UserProfile profile;
}
