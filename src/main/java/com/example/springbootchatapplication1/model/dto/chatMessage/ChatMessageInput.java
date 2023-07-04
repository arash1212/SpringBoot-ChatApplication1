package com.example.springbootchatapplication1.model.dto.chatMessage;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageInput {
    @NotEmpty
    @Size(min = 1, max = 500)
    private String text;
    @NotNull
    private Long chatId;
    //    private Long senderId;
    private Long receiverId;
}
