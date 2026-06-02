package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
