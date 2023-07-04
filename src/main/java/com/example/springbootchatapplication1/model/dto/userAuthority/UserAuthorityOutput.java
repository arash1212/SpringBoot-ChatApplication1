package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserAuthorityOutput {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private String authority;
    private Set<UserBriefOutput> users;
}
