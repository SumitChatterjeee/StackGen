package com.sumit.StackGen.Controllers;

import com.sumit.StackGen.DTO.Member.InviteMemberRequest;
import com.sumit.StackGen.DTO.Member.MemberResponse;
import com.sumit.StackGen.DTO.Member.UpdateMemberRoleRequest;
import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ProjectMemberController {

    ProjectMemberService projectMemberService;
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request)
        );
    }
    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody @Valid UpdateMemberRoleRequest roleRequest
            ) {
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, roleRequest));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void>updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {
       projectMemberService.deleteProjectMember(projectId,memberId);
       return ResponseEntity.noContent().build();
    }
}
