package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId",source = "id.userId")
    @Mapping(target = "role",constant = "OWNER")
    MemberResponse toMemberResponseFromOwner(ProjectMember member);


    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "name",source = "user.name")
    @Mapping(target = "role",source = "projectRole")
    MemberResponse toMemberResponseFromMember(ProjectMember member);
}
