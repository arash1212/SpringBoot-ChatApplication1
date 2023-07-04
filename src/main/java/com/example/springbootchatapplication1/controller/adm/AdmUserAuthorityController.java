package com.example.springbootchatapplication1.controller.adm;

import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityInput;
import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityOutput;
import com.example.springbootchatapplication1.model.service.UserAuthorityService;
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
import java.util.List;

@Tag(name = "Adm-UserAuthority")
@Validated
@RestController
@RequestMapping(path = "/pub/user/authority")
public class AdmUserAuthorityController {
    @Autowired
    private UserAuthorityService userAuthorityService;

    @Operation(summary = "Get userAuthority by id", description = "Get userAuthority by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthorityOutput> getById(@PathVariable(name = "id") Long id,
                                                       @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userAuthorityService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All userAuthorities", description = "Get All userAuthorities")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAuthorityOutput>> getAll(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userAuthorityService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get userAuthority by name", description = "Get userAuthority by name")
    @GetMapping(path = "/by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthorityOutput> getByName(@RequestParam(name = "authorityName") String authorityName,
                                                         @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userAuthorityService.getByName(authorityName), HttpStatus.OK);
    }

    @Operation(summary = "Create new userAuthority", description = "Create new userAuthority")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@Valid @RequestBody UserAuthorityInput input, @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userAuthorityService.save(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Update userAuthority by id", description = "Update userAuthority by id")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAuthorityOutput> update(@PathVariable(name = "id") Long id,
                                                      @Valid @RequestBody UserAuthorityInput input,
                                                      @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(this.userAuthorityService.update(id, input), HttpStatus.OK);
    }

    @ApiResponse(description = "UserAuthority deleted successfully", responseCode = "200")
    @Operation(summary = "Delete userAuthority by id", description = "Delete userAuthority by id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id, @AuthenticationPrincipal Principal principal) {
        this.userAuthorityService.delete(id);
    }

}
