package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Project.FileNode;
import com.sumit.StackGen.Entities.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}