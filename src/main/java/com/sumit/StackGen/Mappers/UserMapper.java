package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.Entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileResponse toUserProfileResponse(User user);
}
