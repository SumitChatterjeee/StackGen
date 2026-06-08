package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlanRepo extends JpaRepository<Plan,Long> {
    Optional<Plan> findByStripePriceId(String id);

    @Query("SELECT p FROM Plan p")
    List<Plan> findAllPlans();
}
