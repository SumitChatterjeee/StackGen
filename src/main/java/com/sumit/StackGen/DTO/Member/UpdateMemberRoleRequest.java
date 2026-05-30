package com.sumit.StackGen.DTO.Member;

import com.sumit.StackGen.Enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role
) {
}
