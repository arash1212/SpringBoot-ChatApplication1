package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ChatRepository extends GenericRepository<ChatEntity> {
    @Override
    public Class<ChatEntity> getDomainClass() {
        return ChatEntity.class;
    }

    public Optional<ChatEntity> getByTitle(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);

        String query = "Select entity from CHAT AS entity left join fetch entity.messages where entity.title like :title";
        List<ChatEntity> userEntityList = super.selectQuery(query, params);
        return userEntityList.size() > 0 ? Optional.of(userEntityList.get(0)) : Optional.empty();
    }
}
