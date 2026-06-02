package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Subscription.PlanResponse;
import com.sumit.StackGen.Services.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
