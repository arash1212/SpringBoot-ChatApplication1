package com.example.springbootchatapplication1.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserBriefOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String username;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
   // private Set<UserAuthorityBriefOutput> authorities;
}
