package com.sumit.StackGen.DTO.Auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
