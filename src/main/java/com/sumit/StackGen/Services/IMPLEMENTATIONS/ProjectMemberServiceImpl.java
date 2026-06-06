package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Member.InviteMemberRequest;
import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.DTO.Member.UpdateMemberRoleRequest;
import com.sumit.StackGen.Entities.Project;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.ProjectMemberId;
import com.sumit.StackGen.Entities.User;
import com.sumit.StackGen.Mappers.ProjectMemberMapper;
import com.sumit.StackGen.Repositories.ProjectMemberRepo;
import com.sumit.StackGen.Repositories.ProjectRepo;
import com.sumit.StackGen.Repositories.UserRepo;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Service
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectRepo projectRepo;
    UserRepo userRepo;
    ProjectMemberRepo projectMemberRepo;
    AuthUtil util;
    ProjectMemberMapper projectMemberMapper;
    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        return projectMemberRepo.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toMemberResponseFromMember)
                .toList();
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
       Long userId=util.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);
        User invitee=userRepo.findByUsername(request.username()).orElseThrow();


        if(invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }


        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        ProjectMember member=ProjectMember.builder().
                id(projectMemberId)
                        .project(project)
                                .user(invitee)
                                        .projectRole(request.role())
                                                .invitedAt(Instant.now())
                                                        .build();

        projectMemberRepo.save(member);


        return projectMemberMapper.toMemberResponseFromMember(member);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest role) {
       Long userId=util.getCurrentUserId();

       Project project=getAccessibleProjectById(projectId,userId);

       ProjectMemberId projectMemberId=new ProjectMemberId(projectId,userId);

       ProjectMember member=projectMemberRepo.findById(projectMemberId).orElseThrow();

       member.setProjectRole(role.role());

       projectMemberRepo.save(member);

       return projectMemberMapper.toMemberResponseFromMember(member);
    }

    @Override
    public void deleteProjectMember(Long projectId, Long memberId) {
        Long userId= util.getCurrentUserId();
        Project project=getAccessibleProjectById(projectId,userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepo.existsById(projectMemberId)){
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepo.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepo.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
