package com.example.springbootchatapplication1.model.entity;

import com.example.springbootchatapplication1.model.entity.interfaces.IEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Table(name = "USER_AUTHORITY")
@Entity(name = "USER_AUTHORITY")
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
}
