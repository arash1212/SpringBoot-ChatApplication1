package com.example.springbootchatapplication1.model.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
    @NotEmpty
    @Size(min = 4, max = 50)
    private String username;
    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;
}
