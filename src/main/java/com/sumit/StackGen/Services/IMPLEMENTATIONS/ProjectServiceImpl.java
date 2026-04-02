package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.DTO.Project.ProjectRequest;
import com.sumit.StackGen.DTO.Project.ProjectResponse;
import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;
import com.sumit.StackGen.Entities.Project;
import com.sumit.StackGen.Entities.User;
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
    ProjectRepo projectReporepo;
    UserRepo userRepo;

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        List<Project>arr=projectReporepo.findByOwnerId(userId);
        List<ProjectSummaryResponse>res=new ArrayList<>();
        for(Project p:arr){
            res.add(new ProjectSummaryResponse(p.getId(),p.getName(),p.getCreatedAt(),p.getUpdatedAt()));
        }
        return res;
    }

    @Override
    public ProjectResponse getUserProjectById(Long projId) {
        Optional<Project>project=projectReporepo.findById(projId);
        User owner=project.get().getOwner();
        UserProfileResponse userProfileResponse=new UserProfileResponse(owner.getId(), owner.getEmail(),owner.getName(),owner.getAvatarUrl());
        ProjectResponse response=new ProjectResponse(project.get().getId(), project.get().getName(),project.get().getCreatedAt(), project.get().getUpdatedAt(),userProfileResponse);
        return response;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User owner=entityManager.getReference(User.class,userId);
        Project project=new Project();
        project.setName(request.name());
        project.setOwner(owner);
        project.setCreatedAt(Instant.now());
        project.setUpdatedAt(Instant.now());
        project.setDeletedAt(null);
        project.setIsPublic(true);
        Project saved=projectReporepo.save(project);
        projectReporepo.flush();

        ProjectResponse response=new ProjectResponse(saved.getId(),saved.getName(),saved.getCreatedAt(),saved.getUpdatedAt(),
                new UserProfileResponse(owner.getId(), owner.getEmail(), owner.getName(),owner.getAvatarUrl()));
        return response;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectReporepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.name());

        Optional<Project>saved= Optional.of(projectReporepo.save(project));
        projectReporepo.flush();
        Optional<User>owner=userRepo.findById(saved.get().getOwner().getId());

         UserProfileResponse userProfileResponse=new UserProfileResponse(owner.get().getId(),owner.get().getEmail(),owner.get().getName(),owner.get().getAvatarUrl());
        ProjectResponse res=new ProjectResponse(id, saved.get().getName(), saved.get().getCreatedAt(), saved.get().getUpdatedAt(),userProfileResponse);
        return res;
    }

    @Override
    public void softDelete(Long id) {
        projectReporepo.deleteById(id);
    }
}
