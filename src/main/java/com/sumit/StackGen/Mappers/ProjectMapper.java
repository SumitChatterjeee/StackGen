//package com.sumit.StackGen.Mappers;
//
//import com.sumit.StackGen.DTO.Project.ProjectResponse;
//import com.sumit.StackGen.DTO.Project.ProjectSummaryResponse;
//import com.sumit.StackGen.Entities.Project;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface ProjectMapper {
//    ProjectResponse toProjectResponse(Project project);
//
//    @Mapping(target = "projectName", source = "name")
//    ProjectSummaryResponse toProjectSummaryResponse(Project project);
//
//    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);
//}
