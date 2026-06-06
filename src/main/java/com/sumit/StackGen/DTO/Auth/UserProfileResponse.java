package com.sumit.StackGen.DTO.Auth;

import lombok.Data;

public record UserProfileResponse(
        Long id,
        String username,
        String name
) {
}
