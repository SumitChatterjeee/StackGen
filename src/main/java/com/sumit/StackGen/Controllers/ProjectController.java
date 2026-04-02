package com.sumit.StackGen.Controllers;

import com.sumit.StackGen.DTO.Project.ProjectRequest;
import com.sumit.StackGen.DTO.Project.ProjectResponse;
import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;
import com.sumit.StackGen.Services.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level=AccessLevel.PRIVATE)
public class ProjectController {

    ProjectService projectService;

    @GetMapping("user_id/{user_id}")
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(@PathVariable Long user_id) {
        return ResponseEntity.ok(projectService.getUserProjects(user_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getUserProjectById(id));
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request,@PathVariable Long user_id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, user_id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id,@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

}
