package com.example.springbootchatapplication1.model.repository.relational;

import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository extends GenericRepository<ChatMessageEntity> {
    @Override
    public Class<ChatMessageEntity> getDomainClass() {
        return ChatMessageEntity.class;
    }
}
