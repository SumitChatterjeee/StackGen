package com.sumit.StackGen.DTO.Auth;

import lombok.Data;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
