package com.sumit.StackGen.DTO.Subscription;

import lombok.Builder;

@Builder
public record PlanResponse(
        Long id,
        String name,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,
        Double price
) {
}
