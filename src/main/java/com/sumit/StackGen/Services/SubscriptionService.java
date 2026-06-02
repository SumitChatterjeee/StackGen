package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Subscription.CheckOutRequest;
import com.sumit.StackGen.DTO.Subscription.CheckOutResponse;
import com.sumit.StackGen.DTO.Subscription.PortalResponse;
import com.sumit.StackGen.DTO.Subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckOutResponse createCheckoutSessionUrl(CheckOutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
