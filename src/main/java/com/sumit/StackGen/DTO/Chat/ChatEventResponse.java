package com.sumit.StackGen.DTO.Chat;

import com.sumit.StackGen.Enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}
