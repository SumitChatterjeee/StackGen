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
    @Override
    public List<PlanResponse> getAllActivePlans() {
       List<Plan> plans=planRepo.findAllPlans();

       List<PlanResponse> responses=new ArrayList<>();
       for(Plan p:plans){
           PlanResponse pl=PlanResponse.builder()
                   .price(p.getPrice())
                   .id(p.getId())
                   .maxProjects(p.getMaxProjects())
                   .name(p.getName())
                   .maxTokensPerDay(p.getMaxTokensPerDay())
                   .unlimitedAi(p.getUnlimitedAi())
                   .build();
           responses.add(pl);
       }
       return responses;
    }
}
