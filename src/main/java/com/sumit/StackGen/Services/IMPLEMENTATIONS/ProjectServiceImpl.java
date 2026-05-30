package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.DTO.Project.ProjectRequest;
import com.sumit.StackGen.DTO.Project.ProjectResponse;
import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;
import com.sumit.StackGen.Entities.Project;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.ProjectMemberId;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Enums.ProjectRole;
import com.sumit.StackGen.Errors.BadRequestException;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.Mappers.ProjectMapper;
import com.sumit.StackGen.Mappers.UserMapper;
import com.sumit.StackGen.Repositories.ProjectMemberRepo;
import com.sumit.StackGen.Repositories.ProjectRepo;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {
    ProjectRepo projectRepo;
    UserRepo userRepo;
    ProjectMapper projectMapper;
    UserMapper userMapper;
    ProjectMemberRepo projectMemberRepo;
    AuthUtil util;


    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId=util.getCurrentUserId();
        var projectsWithRoles=projectRepo.findAllAccessibleByUser(userId);

        return projectsWithRoles.stream()
                .map(p->projectMapper.toProjectSummaryResponse(p.getProject(),p.getRole()))
                .toList();
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectSummaryResponse getUserProjectById(Long projId) {
       Long userId=util.getCurrentUserId();
       var projectWithRole=projectRepo.findAccessibleProjectByIdWithRole(projId,userId)
               .orElseThrow(()-> new BadRequestException("Project Not Found"));

       return projectMapper.toProjectSummaryResponse(projectWithRole.getProject(),projectWithRole.getRole());
    }
    @Override
    public ProjectResponse createProject(ProjectRequest request) {


        Long userId =util.getCurrentUserId();

        User owner=userRepo.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        project = projectRepo.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepo.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId=util.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);
        project.setName(request.name());
        project=projectRepo.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long projectid) {
        Long userId=util.getCurrentUserId();
        Project project=getAccessibleProjectById(projectid,userId);
        project.setDeletedAt(Instant.now());
        projectRepo.save(project);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepo.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));
    }
}
