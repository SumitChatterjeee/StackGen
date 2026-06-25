package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Chat.ChatResponse;
import com.sumit.StackGen.Entities.ChatMessage;
import com.sumit.StackGen.Entities.ChatSession;
import com.sumit.StackGen.Entities.ChatSessionId;
import com.sumit.StackGen.Mappers.ChatMapper;
import com.sumit.StackGen.Repositories.ChatMessageRepo;
import com.sumit.StackGen.Repositories.ChatSessionRepo;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final AuthUtil authUtil;
    private final ChatSessionRepo chatSessionRepo;
    private final ChatMessageRepo chatMessageRepo;
    private final ChatMapper chatMapper;
    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        ChatSession chatSession = chatSessionRepo.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepo.findByChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
