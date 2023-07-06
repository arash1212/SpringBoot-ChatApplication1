package com.example.springbootchatapplication1.model.service.messaging;

import com.example.springbootchatapplication1.model.dto.Messaging.MessageInput;
import com.example.springbootchatapplication1.model.entity.relational.MessageProviderEntity;
import com.example.springbootchatapplication1.model.service.MessageProviderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class GmailMessageProvider implements IMessageProvider {

    private final MessageProviderService messageProviderService;

    @Autowired
    public GmailMessageProvider(MessageProviderService messageProviderService) {
        this.messageProviderService = messageProviderService;
    }

    private static final String TITLE = "GMAIL";

    public JavaMailSender getJavaMailSender(MessageProviderEntity providerEntity) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(providerEntity.getUsername());
        mailSender.setPassword(providerEntity.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendEmail(MessageInput input) {
        Optional<MessageProviderEntity> optionalEntity = this.messageProviderService.getEntityByTitle(TITLE);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        JavaMailSender mailSender = getJavaMailSender(optionalEntity.get());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(optionalEntity.get().getUsername());
        message.setTo(input.getReceiver());
        message.setSubject(input.getSubject());
        message.setText(input.getText());
        mailSender.send(message);
    }

    @Override
    public boolean supportsEmail() {
        return true;
    }
}
