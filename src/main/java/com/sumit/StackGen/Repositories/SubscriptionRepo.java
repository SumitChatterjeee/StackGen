package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.Subscription;
import com.sumit.StackGen.Enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SubscriptionRepo extends JpaRepository<Subscription,Long> {
    List<Subscription> findAllByUserIdAndStatusIn(
            Long userId,
            Set<SubscriptionStatus> statuses
    );

    Optional<Subscription> findByUserIdAndStatusIn(
            Long userId,
            Set<SubscriptionStatus> statusSet
    );

    boolean existsByStripeSubscriptionId(String subscriptionId);

//    boolean existsByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> statusSet);

    Optional<Subscription> findByStripeSubscriptionId(String gatewaySubscriptionId);
}
