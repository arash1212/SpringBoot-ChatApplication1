package com.example.springbootchatapplication1.model.dto.user;

import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityBriefOutput;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
public class UserOutput {
    private Long id;
    private String username;
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
    private Long creatorId;
    private Long lastModifierId;
    private String profilePicture;
}
