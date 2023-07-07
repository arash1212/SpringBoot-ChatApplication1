package com.example.springbootchatapplication1.controller.user;

import com.example.springbootchatapplication1.model.dto.chat.ChatInput;
import com.example.springbootchatapplication1.model.dto.chat.ChatOutput;
import com.example.springbootchatapplication1.model.service.ChatService;
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

@Tag(name = "User-Chat")
@Validated
@RestController
@RequestMapping(path = "/user/chat")
public class UserChatController {
    @Autowired
    private ChatService chatService;

    @Operation(summary = "Create new chat", description = "Create new chat")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@Valid @RequestBody ChatInput input,
                                     @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.chatService.save(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Update chat by id", description = "Update chat by id")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatOutput> update(@PathVariable(name = "id") Long id, @Valid @RequestBody ChatInput input,
                                             @AuthenticationPrincipal Authentication authentication) {
        return new ResponseEntity<>(this.chatService.update(id, input), HttpStatus.OK);
    }

    @ApiResponse(description = "Chat deleted successfully", responseCode = "200")
    @Operation(summary = "Delete chat by id", description = "Delete chat by id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id,
                       @AuthenticationPrincipal Principal principal) {
        this.chatService.delete(id);
    }

}
