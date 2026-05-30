package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Member.InviteMemberRequest;
import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.Entities.Project;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.ProjectMemberId;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.Mappers.ProjectMemberMapper;
import com.sumit.StackGen.Repositories.ProjectMemberRepo;
import com.sumit.StackGen.Repositories.ProjectRepo;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Services.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    ProjectMemberRepo projectMemberRepo;
    UserRepo userRepo;
    ProjectRepo projectRepo;

    ProjectMemberMapper projectMemberMapper;

    @Override
    public List<ProjectMember> getProjectMembers(Long projectId, Long userId) {
            return projectMemberRepo.findByIdProjectId(projectId);
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {

        String email= request.username();
        User user=userRepo.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user",""+userId+"")
                );

        Project project=projectRepo.findById(projectId).orElseThrow(()->
                        new ResourceNotFoundException("project",""+projectId+"")
                );
            User invitee=userRepo.findByUsername(email).orElseThrow(()->
                new ResourceNotFoundException("User with this Email",email)
                );
            ProjectMember projectMember=new ProjectMember();
            projectMember.setId(new ProjectMemberId(projectId,invitee.getId()));
            projectMember.setProject(project);
            projectMember.setUser(invitee);
            projectMember.setProjectRole(request.role());
            projectMember.setInvitedAt(Instant.now());

            projectMemberRepo.save(projectMember);

            return projectMemberMapper.toMemberResponse(projectMember);

    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        return null;
    }
}
