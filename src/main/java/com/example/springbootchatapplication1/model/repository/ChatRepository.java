package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository extends GenericRepository<ChatEntity> {
    @Override
    public Class<ChatEntity> getDomainClass() {
        return ChatEntity.class;
    }
}
