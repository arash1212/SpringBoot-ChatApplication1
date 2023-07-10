package com.example.springbootchatapplication1.controller;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;
import com.example.springbootchatapplication1.model.entity.redis.OtpRedisHash;
import com.example.springbootchatapplication1.model.service.messaging.MessageService;
import com.example.springbootchatapplication1.utils.FileService;
import com.example.springbootchatapplication1.utils.OtpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Testing")
@RestController
@Validated
@RequestMapping(path = "/pub")
public class TestController {

    @Autowired
    private FileService fileService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @GetMapping(path = "/file-service/test")
//    public void createFile() {
//        this.fileService.storeFile();
//    }

    @PostMapping(path = "/test/send/email")
    public void sendEmail(@Valid @RequestBody MessageInput input) {
        messageService.send(input);
    }

    @PostMapping(path = "/test/redis/otp")
    public void testOtpRedis(@Valid @RequestBody OtpRedisHash input) {
        this.otpService.create(input.getUserId());
    }

    @GetMapping(path = "/test/redis/otp/{otp}")
    public ResponseEntity<OtpRedisHash> testGetOtpRedis(@PathVariable(name = "otp") String otp) {
        return new ResponseEntity<>(this.otpService.get(otp), HttpStatus.OK);
    }

    @GetMapping(path = "/test/redis/bcrypt/{text}")
    public ResponseEntity<String> testpasswordencoder(@PathVariable(name = "text") String text) {
        return new ResponseEntity<>(this.passwordEncoder.encode(text), HttpStatus.OK);
    }
}
