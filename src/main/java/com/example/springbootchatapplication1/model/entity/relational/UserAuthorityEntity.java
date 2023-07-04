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
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Table(name = "USER_AUTHORITY")
@Entity(name = "USER_AUTHORITY")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class UserAuthorityEntity implements IEntity, GrantedAuthority {
    @Id
    @SequenceGenerator(name = "UserAuthoritySequence", sequenceName = "USER_AUTHORITY_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserAuthoritySequence")
    private Long id;
    @Basic
    @Column(name = "AUTHORITY", nullable = false, unique = true)
    private String authority;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_AUTHORITY_USER",
            joinColumns = {@JoinColumn(name = "USER_AUTHORITY_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<UserEntity> users;

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
