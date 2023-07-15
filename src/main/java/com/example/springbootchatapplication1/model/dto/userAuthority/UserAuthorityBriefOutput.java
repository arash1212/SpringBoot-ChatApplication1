package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class UserAuthorityBriefOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String authority;
    // private Set<UserBriefOutput> users;

    public UserAuthorityBriefOutput(UserAuthorityEntity entity){
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
        this.authority = entity.getAuthority();
    }
}
