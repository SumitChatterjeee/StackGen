package com.sumit.StackGen.Entities;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PACKAGE)
public class ProjectMemberId {
    Long projectId;
    Long userId;
}
