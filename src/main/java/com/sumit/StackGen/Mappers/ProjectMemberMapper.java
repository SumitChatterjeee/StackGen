package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    MemberResponse toMemberResponse(ProjectMember member);
}
