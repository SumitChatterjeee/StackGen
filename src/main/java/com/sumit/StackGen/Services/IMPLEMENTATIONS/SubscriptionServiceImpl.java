package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Subscription.CheckOutRequest;
import com.sumit.StackGen.DTO.Subscription.CheckOutResponse;
import com.sumit.StackGen.DTO.Subscription.PortalResponse;
import com.sumit.StackGen.DTO.Subscription.SubscriptionResponse;
import com.sumit.StackGen.Services.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckOutResponse createCheckoutSessionUrl(CheckOutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
