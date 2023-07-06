package com.example.springbootchatapplication1.controller.pub;

import com.example.springbootchatapplication1.model.dto.user.UserInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginOut;
import com.example.springbootchatapplication1.model.dto.user.UserOutput;
import com.example.springbootchatapplication1.model.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pub-User")
@Validated
@RestController
@RequestMapping(path = "/pub/user")
public class PubUserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get user by username", description = "Get user by username")
    @GetMapping(path = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutput> getByUsername(@PathVariable(name = "username") String username) {
        return new ResponseEntity<>(this.userService.getByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Register new user", description = "Register new user")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@Valid @RequestBody UserInput input) {
        return ResponseEntity.ok(this.userService.save(input));
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

}
