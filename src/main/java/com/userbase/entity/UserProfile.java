package com.userbase.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents user profile info (embedded in User).
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserProfile {
    private String fullName;

    private String phoneNumber;

    private String avatarUrl;

}
