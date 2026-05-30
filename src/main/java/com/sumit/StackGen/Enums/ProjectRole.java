package com.sumit.StackGen.Enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.sumit.StackGen.Enums.ProjectPermissions.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {

    EDITOR(VIEW, EDIT, DELETE, VIEW_MEMBERS),
    VIEWER(Set.of(VIEW, VIEW_MEMBERS)),
    OWNER(Set.of(VIEW, EDIT, DELETE, MANAGE_MEMBERS, VIEW_MEMBERS));


    ProjectRole(ProjectPermissions... permission) {
        this.permissions = Set.of(permission);
    }

    private final Set<ProjectPermissions> permissions;
}
