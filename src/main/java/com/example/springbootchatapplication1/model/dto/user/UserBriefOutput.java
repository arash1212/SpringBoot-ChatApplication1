package com.example.springbootchatapplication1.model.dto.user;

import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserBriefOutput {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String family;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    // private Set<UserAuthorityBriefOutput> authorities;
    private String profilePicture;

    public UserBriefOutput(UserEntity entity) {
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
