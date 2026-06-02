package com.sumit.StackGen.Entities;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UsageLog {

    Long id;
    User user;
    Project project;

    String action;

    Integer tokensUsed;
    Integer durationMs;

    String metaData; // JSON of {model_used, prompt_used},

    Instant createdAt;
}
