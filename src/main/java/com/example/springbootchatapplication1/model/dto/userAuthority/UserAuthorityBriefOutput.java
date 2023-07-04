package com.example.springbootchatapplication1.model.dto.userAuthority;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserAuthorityBriefOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String authority;
    // private Set<UserBriefOutput> users;
}
