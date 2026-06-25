package com.sumit.StackGen.DTO.Chat;

import com.sumit.StackGen.Entities.ChatEvent;
import com.sumit.StackGen.Entities.ChatSession;
import com.sumit.StackGen.Enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        ChatSession chatSession,
        MessageRole role,
        List<ChatEvent> events,
        String content,
        Integer tokensUsed,
        Instant createdAt

) {
}
