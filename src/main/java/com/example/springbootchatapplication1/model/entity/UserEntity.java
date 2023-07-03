package com.example.springbootchatapplication1.model.entity;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@Table(name = "USERS", indexes = {
        @Index(name = "USER_IDX_USERNAME", columnList = "USERNAME")
})
@Entity(name = "USERS")
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
    @Column(name = "PASSWORD", length = 50, nullable = false)
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
}
