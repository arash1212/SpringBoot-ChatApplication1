package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class UserAuthorityOutput {
    private Long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private String authority;
    private Set<UserBriefOutput> users;
}
