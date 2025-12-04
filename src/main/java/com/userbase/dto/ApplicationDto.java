package com.userbase.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ApplicationDto(
        UUID id,
        String jobId,
        String userSupabaseId,
        String location,
        String jobTitle,
        String companyName,
        String postUrl,
        String notes,
        String status,
        Instant appliedOn,
        Instant createdOn) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}