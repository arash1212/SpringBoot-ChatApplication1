package com.example.springbootchatapplication1.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginOut {
    private String username;
    private String token;
}
