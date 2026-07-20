package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Ai.StreamResponse;
import com.sumit.StackGen.Entities.*;
import com.sumit.StackGen.Enums.ChatEventType;
import com.sumit.StackGen.Enums.MessageRole;
import com.sumit.StackGen.Errors.ResourceNotFoundException;
import com.sumit.StackGen.LLM.Advisors.FileTreeContextAdvisor;
import com.sumit.StackGen.LLM.LlmResponseParser;
import com.sumit.StackGen.LLM.PromptUtil;
import com.sumit.StackGen.LLM.Tools.CodeGenerationTool;
import com.sumit.StackGen.Repositories.*;
import com.sumit.StackGen.Security.AuthUtil;
import com.sumit.StackGen.Services.AiCodeGenerationService;
import com.sumit.StackGen.Services.ProjectFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiCodeGenerationServiceImpl implements AiCodeGenerationService {

    private static final Pattern FILE_TAG_PATTERN = Pattern.compile("<file path=\"([^\"]+)\">(.*?)</file>", Pattern.DOTALL);
    private final ChatClient chatClient;

    private final AuthUtil authUtil;

    private final ProjectFileService projectFileService;

    private final FileTreeContextAdvisor fileTreeContextAdvisor;

    private final ProjectRepo projectRepo;

    private final UserRepo userRepo;

    private final ChatSessionRepo chatSessionRepo;
    private final ChatMessageRepo chatMessageRepo;
    private final ChatEventRepo chatEventRepo;
    private final LlmResponseParser llmResponseParser;


    @Override@PreAuthorize("@security.canEditProject(#projectId)")
    public Flux<String> streamResponse(String userMessage, Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        ChatSession chatSession = createChatSessionIfNotExists(projectId, userId);

        Map<String, Object> advisorParams = Map.of(
                "userId", userId,
                "projectId", projectId
        );

        StringBuilder fullResponseBuffer = new StringBuilder();
        CodeGenerationTool codeGenerationTools = new CodeGenerationTool(projectFileService, projectId);

        AtomicReference<Long> startTime = new AtomicReference<>(System.currentTimeMillis());
        AtomicReference<Long> endTime = new AtomicReference<>(0L);

        return chatClient.prompt()
                .system(PromptUtil.SYSTEM_PROMPT)
                .user(userMessage)
                .tools(codeGenerationTools)
                .advisors(advisorSpec -> {
                            advisorSpec.params(advisorParams);
                            advisorSpec.advisors(fileTreeContextAdvisor);
                        }
                )
                .stream()
                .chatResponse()
                .doOnNext(response -> {
                    String content = response.getResult().getOutput().getText();

                    if(content != null && !content.isEmpty() && endTime.get() == 0) { // first non-empty chunk received
                        endTime.set(System.currentTimeMillis());
                    }

                    fullResponseBuffer.append(content);
                })
                .doOnComplete(() -> {
                    Schedulers.boundedElastic().schedule(() -> {
//                        parseAndSaveFiles(fullResponseBuffer.toString(), projectId);

                        long duration = (endTime.get() - startTime.get()) /  1000;
                        finalizeChats(userMessage, chatSession, fullResponseBuffer.toString(), duration);

                        System.out.println(fullResponseBuffer.toString());
                    });
                })
                .doOnError(error -> log.error("Error during streaming for projectId: {}", projectId))
                .map(response -> Objects.requireNonNull(response.getResult().getOutput().getText()));
    }
    private ChatSession createChatSessionIfNotExists(Long projectId, Long userId) {
        ChatSessionId chatSessionId = new ChatSessionId(projectId, userId);
        ChatSession chatSession = chatSessionRepo.findById(chatSessionId).orElse(null);

        if(chatSession == null) {
            Project project = projectRepo.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

            chatSession = ChatSession.builder()
                    .id(chatSessionId)
                    .project(project)
                    .user(user)
                    .build();

            chatSession = chatSessionRepo.save(chatSession);
        }
        return chatSession;
    }

    private void finalizeChats(String userMessage, ChatSession chatSession, String fullText, Long duration) {
        Long projectId = chatSession.getProject().getId();
            /* Save the User message */
        chatMessageRepo.save(
                ChatMessage.builder()
                        .chatSession(chatSession)
                        .role(MessageRole.USER)
                        .content(userMessage)
                        .build()
        );

        ChatMessage assistantChatMessage = ChatMessage.builder()
                .role(MessageRole.ASSISTANT)
                .content("Assistant Message here...")
                .chatSession(chatSession)
                .build();

        assistantChatMessage = chatMessageRepo.save(assistantChatMessage);

        List<ChatEvent> chatEventList = llmResponseParser.parseChatEvents(fullText, assistantChatMessage);
        chatEventList.addFirst(ChatEvent.builder()
                .type(ChatEventType.THOUGHT)
                .chatMessage(assistantChatMessage)
                .content("Thought for "+duration+"s")
                .sequenceOrder(0)
                .build());

        chatEventList.stream()
                .filter(e -> e.getType() == ChatEventType.FILE_EDIT)
                .forEach(e -> projectFileService.saveFile(projectId, e.getFilePath(), e.getContent()));

        chatEventRepo.saveAll(chatEventList);
    }

}

