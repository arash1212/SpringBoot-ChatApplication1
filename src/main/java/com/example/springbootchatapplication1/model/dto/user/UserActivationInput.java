package com.example.springbootchatapplication1.model.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivationInput {
    @NotNull
    private Long userId;
    @NotEmpty
    @Size(min = 4, max = 4)
    private String otp;
}
