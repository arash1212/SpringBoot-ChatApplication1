package com.example.springbootchatapplication1.model.dto.chat;

import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChatOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String title;
    private List<ChatMessageOutput> messages;
}
