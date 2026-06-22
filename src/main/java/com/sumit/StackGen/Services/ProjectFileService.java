package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Project.FileContentResponse;
import com.sumit.StackGen.DTO.Project.FileNode;
import com.sumit.StackGen.DTO.Project.FileTreeResponse;

import java.util.List;

public interface ProjectFileService {
    FileTreeResponse getFileTree(Long projectId);

    FileContentResponse getFileContent(Long projectId, String path);

    void saveFile(Long projectId, String filePath, String fileContent);
}

