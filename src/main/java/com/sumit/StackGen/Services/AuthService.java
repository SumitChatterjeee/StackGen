package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Auth.AuthResponse;
import com.sumit.StackGen.DTO.Auth.LoginRequest;
import com.sumit.StackGen.DTO.Auth.SignUpRequest;

public interface AuthService {
    AuthResponse login(LoginRequest req);
    AuthResponse signUp(SignUpRequest req);
}
