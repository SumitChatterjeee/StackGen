package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.AuthResponse;
import com.sumit.StackGen.DTO.Auth.LoginRequest;
import com.sumit.StackGen.DTO.Auth.SignUpRequest;
import com.sumit.StackGen.Services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse login(LoginRequest req) {
        return null;
    }

    @Override
    public AuthResponse signUp(SignUpRequest req) {
        return null;
    }
}
