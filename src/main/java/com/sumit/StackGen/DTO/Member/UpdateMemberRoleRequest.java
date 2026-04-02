package com.sumit.StackGen.DTO.Member;

import com.sumit.StackGen.Enums.ProjectRole;

public record UpdateMemberRoleRequest(
        ProjectRole role
) {
}
