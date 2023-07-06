package com.example.springbootchatapplication1.model.dto.messageProvider;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageProviderInput {
    @NotEmpty
    private String title;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
