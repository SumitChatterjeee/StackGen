package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Member.InviteMemberRequest;
import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.DTO.Member.UpdateMemberRoleRequest;
import com.sumit.StackGen.Entities.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);
    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);
    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest role);
    void deleteProjectMember(Long projectId,Long memberId);
}
