package com.example.springbootchatapplication1.model.dto.chatMessage;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ChatMessageOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String text;
    private Long chatId;
    private Long creatorId;
    private Long receiverId;
}
