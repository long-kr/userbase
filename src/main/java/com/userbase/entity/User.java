package com.userbase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    private String timezone = "UTC";

    @Column(nullable = false)
    private UserRole role;

    @Embedded
    private UserProfile profile;
}
