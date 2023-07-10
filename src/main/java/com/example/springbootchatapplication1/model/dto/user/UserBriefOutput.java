package com.example.springbootchatapplication1.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
}
