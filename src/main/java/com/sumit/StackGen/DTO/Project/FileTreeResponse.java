package com.sumit.StackGen.DTO.Project;

import lombok.Builder;

import java.util.List;
@Builder
public record FileTreeResponse(List<FileNode>files) {
}
