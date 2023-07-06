package com.example.springbootchatapplication1.model.repository;

import com.example.springbootchatapplication1.model.entity.relational.enums.entity.EnumMessageTypeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EnumMessageTypeRepository extends GenericRepository<EnumMessageTypeEntity> {
    @Override
    public Class<EnumMessageTypeEntity> getDomainClass() {
        return EnumMessageTypeEntity.class;
    }
}
