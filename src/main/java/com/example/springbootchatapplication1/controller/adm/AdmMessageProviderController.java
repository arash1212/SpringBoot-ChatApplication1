package com.example.springbootchatapplication1.controller.adm;

import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.messageProvider.MessageProviderInput;
import com.example.springbootchatapplication1.model.dto.messageProvider.MessageProviderOutput;
import com.example.springbootchatapplication1.model.service.MessageProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Adm-MessageProvider")
@Validated
@RestController
@RequestMapping(path = "/adm/message/provider")
public class AdmMessageProviderController {
    @Autowired
    private MessageProviderService messageProviderService;

    @Operation(summary = "Get messageProvider by id", description = "Get messageProvider by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageProviderOutput> getById(@PathVariable(name = "id") Long id,
                                                         @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.messageProviderService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All messageProviders", description = "Get All messageProviders")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageProviderOutput>> getAll(@Valid @ModelAttribute BaseFilter filter, @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.messageProviderService.getAll(filter), HttpStatus.OK);
    }

    @Operation(summary = "Get messageProvider by name", description = "Get messageProvider by name")
    @GetMapping(path = "/by-title", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageProviderOutput> getByName(@RequestParam(name = "title") String title,
                                                           @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.messageProviderService.getByTitle(title), HttpStatus.OK);
    }

    @Operation(summary = "Create new messageProvider", description = "Create new messageProvider")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@Valid @RequestBody MessageProviderInput input,
                                     @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.messageProviderService.save(input), HttpStatus.OK);
    }

    @Operation(summary = "Update messageProvider by id", description = "Update messageProvider by id")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageProviderOutput> update(@PathVariable(name = "id") Long id,
                                                        @Valid @RequestBody MessageProviderInput input,
                                                        @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.messageProviderService.update(id, input), HttpStatus.OK);
    }

    @ApiResponse(description = "MessageProvider deleted successfully", responseCode = "200")
    @Operation(summary = "Delete messageProvider by id", description = "Delete messageProvider by id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id,
                       @AuthenticationPrincipal Principal principal) {
        this.messageProviderService.delete(id);
    }

}
