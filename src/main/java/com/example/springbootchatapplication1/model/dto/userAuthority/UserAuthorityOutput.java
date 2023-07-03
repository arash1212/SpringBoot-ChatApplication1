package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import com.example.springbootchatapplication1.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserAuthorityOutput {
    private Long id;
    private String authority;
    private Set<UserBriefOutput> users;
}
