package com.example.springbootchatapplication1.model.dto.user;

import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityBriefOutput;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
public class UserOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String username;
    //    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Set<UserAuthorityBriefOutput> authorities;
}
