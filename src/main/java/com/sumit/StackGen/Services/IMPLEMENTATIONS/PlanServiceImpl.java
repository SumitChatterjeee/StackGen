package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Subscription.PlanResponse;
import com.sumit.StackGen.Entities.Plan;
import com.sumit.StackGen.Mappers.SubscriptionMapper;
import com.sumit.StackGen.Repositories.PlanRepo;
import com.sumit.StackGen.Services.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepo planRepo;
    private final SubscriptionMapper subscriptionMapper;
    @Override
    public List<PlanResponse> getAllActivePlans() {
       List<Plan> plans=planRepo.findAllPlans();

       List<PlanResponse> responses=new ArrayList<>();
       for(Plan p:plans){
           responses.add(subscriptionMapper.toPlanResponse(p));
       }
       return responses;
    }
}
