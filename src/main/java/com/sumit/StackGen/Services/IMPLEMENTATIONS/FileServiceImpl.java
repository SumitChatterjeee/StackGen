package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Project.FileContentResponse;
import com.sumit.StackGen.DTO.Project.FileNode;
import com.sumit.StackGen.Services.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
