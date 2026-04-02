package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Member.InviteMemberRequest;
import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.Entities.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Long projectId,Long userId);
    MemberResponse inviteMember(Long projectId, InviteMemberRequest request,Long userId);
    MemberResponse updateMemberRole(Long projectId,Long memberId,InviteMemberRequest request,Long userId);
    MemberResponse deleteProjectMember(Long projectId,Long memberId,Long userId);
}
