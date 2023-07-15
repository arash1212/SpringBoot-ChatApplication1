package com.example.springbootchatapplication1.model.dto.messageProvider;

import com.example.springbootchatapplication1.model.entity.relational.MessageProviderEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class MessageProviderOutput {
    private Long id;
    private String title;
    //    private String username;
//    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
//    private Long creatorId;
//    private Long lastModifierId;

    public MessageProviderOutput(MessageProviderEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
    }
}
