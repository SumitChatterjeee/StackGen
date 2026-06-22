package com.sumit.StackGen.Controllers;

import com.sumit.StackGen.DTO.Project.FileContentResponse;
import com.sumit.StackGen.DTO.Project.FileNode;
import com.sumit.StackGen.Services.ProjectFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/files")
public class ProjectFileController {

    private final ProjectFileService projectFileService;

    @GetMapping
    public ResponseEntity<List<FileNode>> getFileTree(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectFileService.getFileTree(projectId).files());
    }

    @GetMapping("/{*path}") // /src/hooks/get-user-hook.jsx
    public ResponseEntity<FileContentResponse> getFile(
            @PathVariable Long projectId,
            @PathVariable String path
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(projectFileService.getFileContent(projectId, path));
    }

}
