package com.example.springbootchatapplication1.controller.pub;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Pub-Chat")
@Validated
@RestController
@RequestMapping(path = "/pub/chat")
public class PubChatController {
    @Autowired
    private ChatService chatService;

    @Operation(summary = "Get chat by id", description = "Get chat by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatOutput> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.chatService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All chats", description = "Get All chats")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatOutput>> getAll() {
        return new ResponseEntity<>(this.chatService.getAll(), HttpStatus.OK);
    }
}
