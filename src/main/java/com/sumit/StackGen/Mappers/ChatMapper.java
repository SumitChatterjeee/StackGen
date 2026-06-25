package com.sumit.StackGen.Mappers;

import com.sumit.StackGen.DTO.Chat.ChatResponse;
import com.sumit.StackGen.Entities.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    List<ChatResponse> fromListOfChatMessage(List<ChatMessage> chatMessageList);
}
