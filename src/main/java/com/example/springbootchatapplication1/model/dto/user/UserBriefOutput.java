package com.example.springbootchatapplication1.model.dto.user;

import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityBriefOutput;
import com.example.springbootchatapplication1.model.entity.UserAuthorityEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserBriefOutput {
    private Long id;
    private String username;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
   // private Set<UserAuthorityBriefOutput> authorities;
}
