package com.example.springbootchatapplication1.model.dto.chat;

import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ChatOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String title;
    private List<ChatMessageEntity> messages;
}
