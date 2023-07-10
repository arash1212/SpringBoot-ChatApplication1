package com.example.springbootchatapplication1.model.repository.relational;

import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserAuthorityRepository extends GenericRepository<UserAuthorityEntity> {
    @Override
    public Class<UserAuthorityEntity> getDomainClass() {
        return UserAuthorityEntity.class;
    }

    public Optional<UserAuthorityEntity> getByName(String authority) {
        Map<String, Object> params = new HashMap<>();
        params.put("authority", authority.toLowerCase());

        String query = "Select entity from USER_AUTHORITY AS entity where entity.authority=:authority";
        List<UserAuthorityEntity> authorityEntityList = super.selectQuery(query, params);
        return authorityEntityList.size() > 0 ? Optional.of(authorityEntityList.get(0)) : Optional.empty();
    }
}
