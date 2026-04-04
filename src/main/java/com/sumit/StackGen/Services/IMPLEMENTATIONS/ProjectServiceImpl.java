package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.DTO.Project.ProjectRequest;
import com.sumit.StackGen.DTO.Project.ProjectResponse;
import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;
import com.sumit.StackGen.Entities.Project;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.Mappers.ProjectMapper;
import com.sumit.StackGen.Mappers.UserMapper;
import com.sumit.StackGen.Repositories.ProjectRepo;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Services.ProjectService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
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
@Data
public class ProjectServiceImpl implements ProjectService {
    ProjectRepo projectRepo;
    UserRepo userRepo;
    ProjectMapper projectMapper;
    UserMapper userMapper;

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

        User user=userRepo.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User",userId.toString())
                );
        List<Project>arr=projectRepo.findByOwnerId(userId);
        List<ProjectSummaryResponse>res=new ArrayList<>();
        for(Project p:arr) {
            res.add(projectMapper.toProjectSummaryResponse(p));
        }
        return res;
    }

    @Override
    public ProjectResponse getUserProjectById(Long projId) {
        Project project = projectRepo.findById(projId)
                .orElseThrow(() -> new ResourceNotFoundException("Project",projId.toString()));
        ProjectResponse response=projectMapper.toProjectResponse(project);
        return response;
    }
    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User owner=userRepo.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User",userId.toString())
                );
        Project project=new Project();
        project.setName(request.name());
        project.setOwner(owner);
        project.setCreatedAt(Instant.now());
        project.setUpdatedAt(Instant.now());
        project.setDeletedAt(null);
        project.setIsPublic(true);
        Project saved=projectRepo.save(project);
        projectRepo.flush();

        ProjectResponse response=new ProjectResponse(saved.getId(),saved.getName(),saved.getCreatedAt(),saved.getUpdatedAt(),
                new UserProfileResponse(owner.getId(), owner.getEmail(), owner.getName(),owner.getAvatarUrl()));
        return response;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.name());

        Optional<Project>saved= Optional.of(projectRepo.save(project));
        projectRepo.flush();
        Optional<User>owner=userRepo.findById(saved.get().getOwner().getId());

         UserProfileResponse userProfileResponse=new UserProfileResponse(owner.get().getId(),owner.get().getEmail(),owner.get().getName(),owner.get().getAvatarUrl());
        ProjectResponse res=new ProjectResponse(id, saved.get().getName(), saved.get().getCreatedAt(), saved.get().getUpdatedAt(),userProfileResponse);
        return res;
    }

    @Override
    public void softDelete(Long id) {
        projectRepo.deleteById(id);
    }
}
