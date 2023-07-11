package com.example.springbootchatapplication1.model.repository.relational;

import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRepository extends GenericRepository<ChatEntity> {
    @Override
    public Class<ChatEntity> getDomainClass() {
        return ChatEntity.class;
    }

    public Optional<ChatEntity> getByTitle(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title.toLowerCase());

        String query = "Select entity from CHAT AS entity left join fetch entity.messages where entity.title=:title ";
        List<ChatEntity> chatEntityList = super.selectQuery(query, params);
        return chatEntityList.size() > 0 ? Optional.of(chatEntityList.get(0)) : Optional.empty();
    }
}
