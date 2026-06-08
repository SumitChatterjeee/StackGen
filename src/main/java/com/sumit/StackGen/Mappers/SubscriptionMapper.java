package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Subscription.PlanResponse;
import com.sumit.StackGen.DTO.Subscription.SubscriptionResponse;
import com.sumit.StackGen.Entities.Plan;
import com.sumit.StackGen.Entities.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanResponse toPlanResponse(Plan plan);
}
