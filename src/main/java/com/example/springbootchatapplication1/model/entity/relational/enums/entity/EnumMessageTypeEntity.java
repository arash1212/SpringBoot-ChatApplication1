package com.example.springbootchatapplication1.model.entity.relational.enums.entity;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Table(name = "ENUM_MESSAGE_TYPE", indexes = {})
@Entity(name = "ENUM_MESSAGE_TYPE")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class EnumMessageTypeEntity implements IEntity {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", length = 255, nullable = false, unique = true)
    private String title;
}
