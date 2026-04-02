package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Project.ProjectRequest;
import com.sumit.StackGen.DTO.Project.ProjectResponse;
import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request,Long userId);

    ProjectResponse updateProject(Long id,ProjectRequest request);

    void softDelete(Long id);
}
