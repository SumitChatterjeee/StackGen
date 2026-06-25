package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.ChatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEventRepo extends JpaRepository<ChatEvent,Long> {

}
