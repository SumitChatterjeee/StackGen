package com.sumit.StackGen.DTO.Subscription;

public record PlanLimitsResponse(
        String planName,
        int maxTokensPerDay,
        int maxProject,
        boolean unlimitedAi
) {
}
