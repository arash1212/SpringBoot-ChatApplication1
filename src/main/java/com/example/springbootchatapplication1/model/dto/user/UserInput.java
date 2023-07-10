package com.example.springbootchatapplication1.model.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserInput {
    @NotEmpty
    @Size(min = 4, max = 50)
    private String username;
    @NotEmpty
    @Size(min = 4, max = 50)
    private String email;
    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;
    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;
    @NotEmpty
    @Size(min = 4, max = 50)
    private String family;
    private String profilePicture;
}
