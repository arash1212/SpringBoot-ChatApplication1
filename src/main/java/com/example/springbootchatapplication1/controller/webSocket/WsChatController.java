package com.example.springbootchatapplication1.controller.webSocket;

import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageInput;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class WsChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/chat/{chatId}")
    public ChatMessageOutput chat(ChatMessageInput input, @DestinationVariable("chatId") Long chatId, @AuthenticationPrincipal Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return this.chatMessageService.save(input);
    }
}
