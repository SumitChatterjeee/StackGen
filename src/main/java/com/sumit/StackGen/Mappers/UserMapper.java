package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Auth.SignUpRequest;
import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.Entities.User;
import org.hibernate.boot.internal.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username" ,target = "username")
    UserProfileResponse toUserProfileResponse(User user);

    @Mapping(source = "username" ,target = "username")
    User toEntity(SignUpRequest sign);
}
