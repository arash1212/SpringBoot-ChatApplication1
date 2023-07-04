package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends GenericRepository<UserEntity> {
    @Override
    public Class<UserEntity> getDomainClass() {
        return UserEntity.class;
    }

    public Optional<UserEntity> getByUsername(String username) {
        String query = "Select entity from USERS AS entity left join fetch entity.authorities where entity.username=:username";
        TypedQuery<UserEntity> typedQuery = (TypedQuery<UserEntity>) super.entityManager.createQuery(query);
        typedQuery.setParameter("username", username);
        return Optional.of(typedQuery.getSingleResult());
    }
}
