package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
}
