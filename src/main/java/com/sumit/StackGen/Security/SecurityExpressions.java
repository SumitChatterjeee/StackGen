package com.sumit.StackGen.Security;

import com.sumit.StackGen.Enums.ProjectPermissions;
import com.sumit.StackGen.Repositories.ProjectMemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {
    private final ProjectMemberRepo projectMemberRepo;
    private  final AuthUtil util;

    private boolean hasPermission(Long projectId, ProjectPermissions permissions){
        Long userId=util.getCurrentUserId();
        return projectMemberRepo.findRoleByProjectIdAndUserId(projectId,userId)
                .map(role-> role.getPermissions().contains(permissions))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId) {
        return hasPermission(projectId, ProjectPermissions.VIEW);
    }

    public boolean canEditProject(Long projectId) {
        return hasPermission(projectId, ProjectPermissions.EDIT);
    }

    public boolean canDeleteProject(Long projectId) {
        return hasPermission(projectId, ProjectPermissions.DELETE);
    }

    public boolean canViewMembers(Long projectId) {
        return hasPermission(projectId, ProjectPermissions.VIEW_MEMBERS);
    }

    public boolean canManageMembers(Long projectId) {
        return hasPermission(projectId, ProjectPermissions.MANAGE_MEMBERS);
    }
}
