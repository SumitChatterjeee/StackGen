package com.sumit.StackGen.DTO.Subscription;

public record PlanLimitsResponse(
        String planNmae,
        int maxTokensPerDay,
        int maxProject,
        boolean unlimitedAi
) {
}
