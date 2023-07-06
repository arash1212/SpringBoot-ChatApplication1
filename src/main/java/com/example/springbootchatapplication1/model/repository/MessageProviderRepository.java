package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.MessageProviderEntity;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MessageProviderRepository extends GenericRepository<MessageProviderEntity> {
    @Override
    public Class<MessageProviderEntity> getDomainClass() {
        return MessageProviderEntity.class;
    }

    public Optional<MessageProviderEntity> getByTitle(String title) {
        String query = "Select entity from MESSAGE_PROVIDER AS entity where entity.title=:title";
        TypedQuery<MessageProviderEntity> typedQuery = (TypedQuery<MessageProviderEntity>) super.entityManager.createQuery(query);
        typedQuery.setParameter("title", title);
        return typedQuery.getResultList().size() > 0 ? Optional.of(typedQuery.getResultList().get(0)) : Optional.empty();
    }
}
