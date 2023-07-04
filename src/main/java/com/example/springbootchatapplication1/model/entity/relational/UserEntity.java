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
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Table(name = "USERS", indexes = {
        @Index(name = "USER_IDX_USERNAME", columnList = "USERNAME")
})
@Entity(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class UserEntity implements IEntity, UserDetails {
    @Id
    @SequenceGenerator(name = "UserSequence", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "USERNAME", length = 50, nullable = false, unique = true)
    private String username;
    @Basic
    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;
    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired;
    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    private boolean accountNonLocked;
    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    private boolean credentialsNonExpired;
    @Basic
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<UserAuthorityEntity> authorities;

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
