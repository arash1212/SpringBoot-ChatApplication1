package com.example.springbootchatapplication1.model.dto.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatInput {
    @NotEmpty
    @Size(min = 4, max = 255)
    private String title;
}
