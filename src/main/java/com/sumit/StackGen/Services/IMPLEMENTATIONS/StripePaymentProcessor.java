package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.sumit.StackGen.DTO.Subscription.CheckOutRequest;
import com.sumit.StackGen.DTO.Subscription.CheckOutResponse;
import com.sumit.StackGen.DTO.Subscription.PortalResponse;
import com.sumit.StackGen.Entities.Plan;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.Repositories.PlanRepo;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripePaymentProcessor implements PaymentProcessor {

    private final AuthUtil util;
    private final PlanRepo planRepo;
    private final UserRepo userRepo;

    @Value("${client.url}")
    private String frontendUrl;

    @Override
    public CheckOutResponse createCheckoutSessionUrl(CheckOutRequest request) {
        Long userId= util.getCurrentUserId();
        Plan plan = planRepo.findById(request.planId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plan Not Found",
                                request.planId().toString()
                        )
                );

        User user=userRepo.findById(userId).
                orElseThrow(() ->
                new ResourceNotFoundException(
                        "User Not Found",
                        userId.toString()
                )
        );
        String stripeCustomerId=user.getStripeCustomerId();

        var params =
                SessionCreateParams.builder()
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setPrice(plan.getStripePriceId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                        .setSubscriptionData(
                                new SessionCreateParams.SubscriptionData.Builder()
                                        .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                                .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE)
                                                .build())
                                        .build()
                        )
                        .setSuccessUrl(frontendUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl(frontendUrl + "/cancel.html")
                        .putMetadata("user_id", userId.toString())
                        .putMetadata("plan_id", plan.getId().toString());

                            if(stripeCustomerId==null||stripeCustomerId.isEmpty()){
                                params.setCustomerEmail(user.getUsername());
                            }
                            else{
                                params.setCustomer(stripeCustomerId);
                            }
        try{

            Session session = Session.create(params.build());
            return new CheckOutResponse(session.getUrl());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PortalResponse openCustomerPortal() {
        return null;
    }

    @Override
    public void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata) {
        log.info("Hey iam There");
    }
}
