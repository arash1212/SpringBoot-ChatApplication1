package com.example.springbootchatapplication1.model.dto.user;

import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityBriefOutput;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class UserOutput {
    private Long id;
    private String username;
    private String email;
    //    private String password;
    private String name;
    private String family;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Set<UserAuthorityBriefOutput> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
//    private Long creatorId;
//    private Long lastModifierId;
    private String profilePicture;

    public UserOutput(UserEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.name = entity.getEmail();
        this.family = entity.getFamily();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
        this.accountNonExpired = entity.isAccountNonExpired();
        this.accountNonLocked = entity.isAccountNonLocked();
        this.credentialsNonExpired = entity.isCredentialsNonExpired();
        this.enabled = entity.isEnabled();
        this.profilePicture = entity.getProfilePicture();
    }
}
