package com.example.springbootchatapplication1.controller.user;

import com.example.springbootchatapplication1.model.dto.user.UserInput;
import com.example.springbootchatapplication1.model.dto.user.UserOutput;
import com.example.springbootchatapplication1.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "User-User")
@Validated
@RestController
@RequestMapping(path = "/user/user")
public class UserUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Update user infos", description = "Update User Infos")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserInput input,
                                             @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userService.update(id, input), HttpStatus.OK);
    }

    @ApiResponse(description = "User deleted successfully", responseCode = "200")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id, @AuthenticationPrincipal Principal principal) {
        this.userService.delete(id);
    }
}
