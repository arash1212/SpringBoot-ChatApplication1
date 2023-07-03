package com.example.springbootchatapplication1.model.dto.userAuthority;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthorityInput {
    @NotEmpty
    @Size(min = 3, max = 50)
    private String authority;
}
