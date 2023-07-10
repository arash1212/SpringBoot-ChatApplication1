package com.example.springbootchatapplication1.model.service.messaging;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;
import com.example.springbootchatapplication1.model.entity.relational.enums.EnumMessageType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class MessageService {

    @Autowired
    private ApplicationContext context;

    public void send(MessageInput input) {
        if (Objects.equals(input.getMessageTypeId(), EnumMessageType.SMS.getId())) {

        } else if (Objects.equals(input.getMessageTypeId(), EnumMessageType.EMAIL.getId())) {
            sendEmail(input);
        }
    }

    public void sendEmail(MessageInput input) {
        IMessageProvider messageProvider = this.getProvider();
        if (messageProvider != null) {
            messageProvider.sendEmail(input);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private IMessageProvider getProvider() {
        Map<String, IMessageProvider> providers = context.getBeansOfType(IMessageProvider.class);
        for (Map.Entry<String, IMessageProvider> provider : providers.entrySet()) {
            if (provider.getValue().supportsEmail()) {
                return provider.getValue();
            }
        }
        return null;
    }
}
