package com.example.springbootchatapplication1.controller;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;
import com.example.springbootchatapplication1.model.service.messaging.GmailMessageProvider;
import com.example.springbootchatapplication1.model.service.messaging.MessageService;
import com.example.springbootchatapplication1.utils.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Testing")
@RestController
@Validated
@RequestMapping(path = "/pub")
public class TestController {

    @Autowired
    private FileService fileService;
    @Autowired
    private MessageService messageService;

//    @GetMapping(path = "/file-service/test")
//    public void createFile() {
//        this.fileService.storeFile();
//    }

    @PostMapping(path = "/send/email")
    public void sendEmail(@Valid @RequestBody MessageInput input) {
        messageService.send(input);
    }
}
