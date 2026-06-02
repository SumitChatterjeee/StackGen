package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Subscription.PlanLimitsResponse;
import com.sumit.StackGen.DTO.Subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userid);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
