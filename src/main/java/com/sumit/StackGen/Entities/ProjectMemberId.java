package com.sumit.StackGen.Entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PACKAGE)
@Builder
@NoArgsConstructor
public class ProjectMemberId {
    Long projectId;
    Long userId;
}
