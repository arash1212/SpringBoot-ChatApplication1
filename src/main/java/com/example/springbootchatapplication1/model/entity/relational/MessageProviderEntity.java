package com.example.springbootchatapplication1.model.entity.relational;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "MESSAGE_PROVIDER", indexes = {
        @Index(name = "MESSAGE_PROVIDER_IDX_TITLE", columnList = "TITLE")
})
@Entity(name = "MESSAGE_PROVIDER")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class MessageProviderEntity implements IEntity {
    @Id
    @SequenceGenerator(name = "MessageProviderSequence", sequenceName = "MESSAGE_PROVIDER_SEQ", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageProviderSequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "PASSWORD")
    private String password;

    /******************************************************************************************************************/
    @Basic
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @LastModifiedDate
    @Column(name = "LAST_UPDATE_AT", nullable = false)
    private LocalDateTime lastUpdateAt;

    @Basic
    @CreatedBy
    @Column(name = "CREATOR_ID", insertable = true, updatable = true, nullable = true)
    private Long creatorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID", insertable = false, updatable = false, nullable = true)
    private UserEntity creator;
    @Basic
    @LastModifiedBy
    @Column(name = "LAST_MODIFIER_ID", insertable = true, updatable = true, nullable = true)
    private Long lastModifierId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LAST_MODIFIER_ID", insertable = false, updatable = false, nullable = true)
    private UserEntity lastModifier;
    /******************************************************************************************************************/
}
