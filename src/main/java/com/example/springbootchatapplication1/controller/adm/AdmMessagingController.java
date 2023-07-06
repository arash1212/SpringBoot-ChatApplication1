package com.example.springbootchatapplication1.controller.adm;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;
import com.example.springbootchatapplication1.model.service.messaging.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Adm-Messaging")
@Validated
@RestController
@RequestMapping(path = "/adm/messaging/")
public class AdmMessagingController {

    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/send")
    public void sendEmail(@Valid @RequestBody MessageInput input) {
        messageService.send(input);
    }
}
