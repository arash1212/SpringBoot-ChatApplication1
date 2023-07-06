package com.example.springbootchatapplication1.model.service.messaging;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;

public interface IMessageProvider {
    void sendEmail(MessageInput input);

    public boolean supportsEmail();
}
