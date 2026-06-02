package com.sumit.StackGen.DTO.Member;

import com.sumit.StackGen.Enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole role,
        Instant invitedAt
) {
}
