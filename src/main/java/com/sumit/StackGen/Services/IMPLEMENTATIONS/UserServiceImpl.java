package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.DTO.Subscription.PlanLimitsResponse;
import com.sumit.StackGen.DTO.Subscription.UsageTodayResponse;
import com.sumit.StackGen.Services.UsageService;
import com.sumit.StackGen.Services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
