package com.example.springbootchatapplication1.model.dto.chat;

import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ChatOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String title;
    private List<ChatMessageOutput> messages;

    public ChatOutput(ChatEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
        this.title = entity.getTitle();
        this.messages = entity.getMessages().stream().map(ChatMessageOutput::new).collect(Collectors.toList());
    }
}
