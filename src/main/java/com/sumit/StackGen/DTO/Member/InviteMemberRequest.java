package com.sumit.StackGen.DTO.Member;

import com.sumit.StackGen.Enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotBlank@Email String username,
        @NotNull ProjectRole role
) {
}
