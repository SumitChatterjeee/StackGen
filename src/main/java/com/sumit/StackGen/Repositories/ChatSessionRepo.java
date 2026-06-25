package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.ChatSession;
import com.sumit.StackGen.Entities.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepo extends JpaRepository<ChatSession, ChatSessionId> {

}
