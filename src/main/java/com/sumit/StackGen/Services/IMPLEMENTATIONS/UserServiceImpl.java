package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Auth.UserProfileResponse;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.Mappers.UserMapper;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo repo;
    private final UserMapper userMapper;
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return userMapper.toUserProfileResponse(repo.findById(userId).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", username));
    }
}
