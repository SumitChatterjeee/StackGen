package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Project.FileContentResponse;
import com.sumit.StackGen.DTO.Project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}

