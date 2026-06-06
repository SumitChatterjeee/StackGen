package com.sumit.StackGen.Services;

import com.stripe.model.StripeObject;
import com.sumit.StackGen.DTO.Subscription.CheckOutRequest;
import com.sumit.StackGen.DTO.Subscription.CheckOutResponse;
import com.sumit.StackGen.DTO.Subscription.PortalResponse;

import java.util.Map;

public interface PaymentProcessor {
    CheckOutResponse createCheckoutSessionUrl(CheckOutRequest request);

    PortalResponse openCustomerPortal();

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
