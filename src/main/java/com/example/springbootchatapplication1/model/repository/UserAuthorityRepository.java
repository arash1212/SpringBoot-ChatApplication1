package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.UserAuthorityEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthorityRepository extends GenericRepository<UserAuthorityEntity> {
    @Override
    public Class<UserAuthorityEntity> getDomainClass() {
        return UserAuthorityEntity.class;
    }
}
