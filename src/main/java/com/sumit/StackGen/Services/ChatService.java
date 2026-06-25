package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Chat.ChatResponse;

import java.util.List;

public interface ChatService {
    List<ChatResponse> getProjectChatHistory(Long projectId);
}
