package com.example.springbootchatapplication1.model.dto.userAuthority;

import com.example.springbootchatapplication1.model.dto.user.UserBriefOutput;
import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class UserAuthorityOutput {
    private Long id;
    private String authority;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private List<UserBriefOutput> users;

    public UserAuthorityOutput(UserAuthorityEntity entity) {
        this.id = entity.getId();
        this.authority = entity.getAuthority();
        this.createdAt = entity.getCreatedAt();
        this.lastUpdateAt = entity.getLastUpdateAt();
        this.users = entity.getUsers().stream().filter(Objects::nonNull).map(UserBriefOutput::new).collect(Collectors.toList());
    }
}
