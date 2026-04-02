package com.sumit.StackGen.DTO.Member;

import com.sumit.StackGen.Enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
