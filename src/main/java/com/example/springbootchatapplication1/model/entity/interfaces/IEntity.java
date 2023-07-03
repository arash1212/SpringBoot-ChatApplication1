package com.example.springbootchatapplication1.model.entity.interfaces;

import jakarta.persistence.Transient;

public interface IEntity {
    void setId(Long Id);

    Long getId();

//    @Transient
//    Class getDomainClass();
}
