package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAuthorityRepository extends GenericRepository<UserAuthorityEntity> {
    @Override
    public Class<UserAuthorityEntity> getDomainClass() {
        return UserAuthorityEntity.class;
    }

    public Optional<UserAuthorityEntity> getByName(String authority) {
        String query = "Select entity from USER_AUTHORITY AS entity where entity.authority=:authority";
        TypedQuery<UserAuthorityEntity> typedQuery = (TypedQuery<UserAuthorityEntity>) super.entityManager.createQuery(query);
        typedQuery.setParameter("authority", authority);
        return typedQuery.getResultList().size() > 0 ? Optional.of(typedQuery.getResultList().get(0)) : Optional.empty();
    }
}
