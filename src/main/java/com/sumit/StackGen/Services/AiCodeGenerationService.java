package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Ai.StreamResponse;
import reactor.core.publisher.Flux;

public interface AiCodeGenerationService {
    Flux<String> streamResponse(String message, Long projectId);
}
