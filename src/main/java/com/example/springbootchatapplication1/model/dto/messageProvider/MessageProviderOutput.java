package com.example.springbootchatapplication1.model.dto.messageProvider;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageProviderOutput {
    private Long id;
    private String title;
//    private String username;
//    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
//    private Long creatorId;
//    private Long lastModifierId;
}
