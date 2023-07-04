package com.example.springbootchatapplication1.model.dto.chatMessage;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String text;
    private Long chatId;
    private Long creatorId;
    private Long receiverId;
}
