package com.example.springbootchatapplication1.model.dto.chatMessage;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String text;
    private Long chatId;
    private UserBriefOutput creator;
    private UserBriefOutput receiver;

    public ChatMessageOutput(ChatMessageEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
        this.text = entity.getText();
        this.chatId = entity.getChatId();
        this.creator = new UserBriefOutput(entity.getCreator());
        this.receiver = new UserBriefOutput(entity.getReceiver());
    }
}
