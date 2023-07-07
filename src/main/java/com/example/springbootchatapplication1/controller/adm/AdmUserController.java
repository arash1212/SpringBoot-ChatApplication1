package com.example.springbootchatapplication1.controller.adm;

import com.example.springbootchatapplication1.model.dto.user.UserOutput;
import com.example.springbootchatapplication1.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Adm-User")
@Validated
@RestController
@RequestMapping(path = "/adm/user")
public class AdmUserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get All users", description = "Get All users")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserOutput>> getAll(@AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Assign authority to  user", description = "Assign authority to  user")
    @PatchMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> assignAuthority(@PathVariable(name = "userId") Long userId,
                                                      @RequestBody List<Long> authorityIds,
                                                      @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.userService.assignAuthority(userId, authorityIds), HttpStatus.OK);
    }

    @Operation(summary = "Disable user account", description = "Disable user account")
    @PatchMapping(path = "/disable/{id}")
    public void disable(@PathVariable(name = "id") Long id, @AuthenticationPrincipal Authentication authentication) {
        this.userService.disable(id);
    }
}
