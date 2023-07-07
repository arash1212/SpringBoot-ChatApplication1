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
import java.util.Set;


@Getter
@Setter
@Table(name = "CHAT", indexes = {
//        @Index(name = "CHAT_IDX_TITLE", columnList = "TITLE")
})
@Entity(name = "CHAT")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class ChatEntity implements IEntity {
    @Id
    @SequenceGenerator(name = "ChatSequence", sequenceName = "CHAT_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatSequence")
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", length = 255, nullable = false, unique = true)
    private String title;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private Set<ChatMessageEntity> messages;

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
    @Column(name = "CREATOR_ID", insertable = true, updatable = true, nullable = false)
    private Long creatorId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID", insertable = false, updatable = false, nullable = false)
    private UserEntity creator;
    @Basic
    @LastModifiedBy
    @Column(name = "LAST_MODIFIER_ID", insertable = true, updatable = true, nullable = false)
    private Long lastModifierId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LAST_MODIFIER_ID", insertable = false, updatable = false, nullable = false)
    private UserEntity lastModifier;
    /******************************************************************************************************************/


}
