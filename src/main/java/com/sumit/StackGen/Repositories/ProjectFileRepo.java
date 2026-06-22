package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectFileRepo extends JpaRepository<ProjectFile,Long> {

    Optional<ProjectFile> findByProjectIdAndPath(Long projectId, String cleanPath);

    List<ProjectFile> findByProjectId(Long projectId);
}
