package com.example.springbootchatapplication1.model.dto.userAuthority;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserAuthorityBriefOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String authority;
    // private Set<UserBriefOutput> users;
}
