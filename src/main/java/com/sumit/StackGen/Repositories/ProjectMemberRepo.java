package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.ProjectMember;
import com.sumit.StackGen.Entities.ProjectMemberId;
import com.sumit.StackGen.Enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepo extends JpaRepository<ProjectMember, ProjectMemberId> {
    List<ProjectMember>findByIdProjectId(Long projectId);
    @Query("""
            SELECT pm.projectRole FROM ProjectMember pm
            WHERE pm.id.projectId = :projectId AND pm.id.userId = :userId
            """)
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                       @Param("userId") Long userId);


    @Query("""
            SELECT COUNT(pm) FROM ProjectMember pm
            WHERE pm.id.userId = :userId AND pm.projectRole = 'OWNER'
            """)
    int countProjectOwnedByUser(@Param("userId") Long userId);

}
