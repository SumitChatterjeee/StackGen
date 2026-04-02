package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findByOwnerId(Long user_id);
    Optional<Project> findById(Long proj_id);

    @Override
    void delete(Project entity);
}
