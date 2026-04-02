package com.sumit.StackGen.DTO.Auth;

public record SignUpRequest(
        String email,
        String name,
        String password
) {
}
