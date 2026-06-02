package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
