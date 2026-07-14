package com.sumit.StackGen.Controllers;

import com.sumit.StackGen.DTO.Auth.AuthResponse;
import com.sumit.StackGen.DTO.Auth.LoginRequest;
import com.sumit.StackGen.DTO.Auth.SignUpRequest;
import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Security.JwtUserPrincipal;
import com.sumit.StackGen.Services.AuthService;
import com.sumit.StackGen.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse=authService.login(request);
        System.out.println("RESPONSE = " + authResponse);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }

}

