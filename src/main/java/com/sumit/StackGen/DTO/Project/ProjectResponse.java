package com.sumit.StackGen.DTO.Project;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updateAt,
        UserProfileResponse owner
) {
}
