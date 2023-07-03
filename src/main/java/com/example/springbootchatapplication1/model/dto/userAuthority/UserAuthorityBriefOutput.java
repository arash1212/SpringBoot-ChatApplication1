package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserAuthorityBriefOutput {
    private Long id;
    private String authority;
   // private Set<UserBriefOutput> users;
}
