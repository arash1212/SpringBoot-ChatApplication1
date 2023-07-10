package com.example.springbootchatapplication1.model.repository.relational;

import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository extends GenericRepository<UserEntity> {
    @Override
    public Class<UserEntity> getDomainClass() {
        return UserEntity.class;
    }

    public Optional<UserEntity> getByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username.toLowerCase());

        String query = "Select entity from USERS AS entity left join fetch entity.authorities where entity.username=:username";
        List<UserEntity> userEntityList = super.selectQuery(query, params);
        return userEntityList.size() > 0 ? Optional.of(userEntityList.get(0)) : Optional.empty();
    }

    public Optional<UserEntity> getByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email.toLowerCase());

        String query = "Select entity from USERS AS entity left join fetch entity.authorities where entity.email=:email";
        List<UserEntity> userEntityList = super.selectQuery(query, params);
        return userEntityList.size() > 0 ? Optional.of(userEntityList.get(0)) : Optional.empty();
    }
}
