package com.example.springbootchatapplication1.controller;

import com.example.springbootchatapplication1.model.dto.user.UserInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginOut;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@Validated
@RestController
@RequestMapping(path = "/pub/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All users", description = "Get All users")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserOutput>> getAll() {
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username", description = "Get user by username")
    @GetMapping(path = "/by-username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> getByUsername(@RequestParam(name = "username") String username) {
        return new ResponseEntity<>(this.userService.getByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Register new user", description = "Register new user")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@Valid @RequestBody UserInput input) {
        return new ResponseEntity<>(this.userService.save(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Update user infos", description = "Update User Infos")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserInput input) {
        return new ResponseEntity<>(this.userService.update(id, input), HttpStatus.OK);
    }

    @ApiResponse(description = "User deleted successfully", responseCode = "200")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.userService.delete(id);
    }

    @Operation(summary = "Assign authority to  user", description = "Assign authority to  user")
    @PatchMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> assignAuthority(@PathVariable(name = "userId") Long userId, @RequestBody List<Long> authorityIds) {
        return new ResponseEntity<>(this.userService.assignAuthority(userId, authorityIds), HttpStatus.OK);
    }

    @Operation(summary = "User login - Generate token", description = "User login - Generate token")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginOut> login(@Valid @RequestBody UserLoginInput input) {
        return new ResponseEntity<>(this.userService.login(input), HttpStatus.OK);
    }

    @Operation(summary = "Enable user account", description = "Enable user account")
    @PatchMapping(path = "/enable/{id}")
    public void enable(@PathVariable(name = "id") Long id) {
        this.userService.enable(id);
    }

    @Operation(summary = "Disable user account", description = "Disable user account")
    @PatchMapping(path = "/disable/{id}")
    public void disable(@PathVariable(name = "id") Long id) {
        this.userService.disable(id);
    }

}
