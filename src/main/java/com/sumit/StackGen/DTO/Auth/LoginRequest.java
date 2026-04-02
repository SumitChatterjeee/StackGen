package com.sumit.StackGen.DTO.Auth;

public record LoginRequest(
        String email,
        String password
) {
}
