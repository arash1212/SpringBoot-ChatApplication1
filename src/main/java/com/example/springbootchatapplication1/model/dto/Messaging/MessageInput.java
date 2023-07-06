package com.example.springbootchatapplication1.model.dto.Messaging;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInput {
    @NotEmpty
    private String receiver;
    @NotEmpty
    private String text;
    @NotEmpty
    private String subject;
    @NotNull
    private Long messageTypeId;
}
