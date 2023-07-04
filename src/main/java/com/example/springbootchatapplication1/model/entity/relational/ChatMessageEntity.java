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
import java.util.Date;

@Getter
@Setter
@Table(name = "CHAT_MESSAGE", indexes = {
})
@Entity(name = "CHAT_MESSAGE")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class ChatMessageEntity implements IEntity {
    @Id
    @SequenceGenerator(name = "ChatMessageSequence", sequenceName = "CHAT_MESSAGE_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChatMessageSequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "TEXT", length = 500, nullable = false)
    private String text;

    @Basic
    @Column(name = "CHAT_ID", nullable = false, insertable = true, updatable = true)
    private Long chatId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ID", nullable = false, insertable = false, updatable = false)
    private ChatEntity chat;
    @Basic
    @Column(name = "RECEIVER_ID", nullable = true, insertable = true, updatable = true)
    private Long receiverId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID", nullable = true, insertable = false, updatable = false)
    private UserEntity receiver;

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
