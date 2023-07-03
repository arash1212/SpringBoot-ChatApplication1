package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<UserEntity> {
    @Override
    public Class<UserEntity> getDomainClass() {
        return UserEntity.class;
    }
}
