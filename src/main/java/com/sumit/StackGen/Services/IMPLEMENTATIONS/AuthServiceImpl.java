package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.AuthResponse;
import com.sumit.StackGen.DTO.Auth.LoginRequest;
import com.sumit.StackGen.DTO.Auth.SignUpRequest;
import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Entities.UserSearchDocument;
import com.sumit.StackGen.Mappers.UserMapper;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Repositories.UserSearchDocRepo;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Security.JwtUserPrincipal;
import com.sumit.StackGen.Services.AuthService;
import lombok.AccessLevel;
import com.sumit.StackGen.Errors.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepo userRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil util;
    AuthenticationManager authenticationManager;
    UserSearchDocRepo userSearchDocRepo;

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();

        String token=util.generateAccessToken(user);

        UserProfileResponse response=userMapper.toUserProfileResponse(user);
        return new AuthResponse(token,response);

    }

    @Override
    public AuthResponse signUp(SignUpRequest request) {

        userRepo.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username: "+request.username());
        });

        User user = new User();
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setName(request.name());
        user = userRepo.save(user);

        UserSearchDocument userSearchDocument=UserSearchDocument.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();

        userSearchDocRepo.save(userSearchDocument);

        String token = util.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
