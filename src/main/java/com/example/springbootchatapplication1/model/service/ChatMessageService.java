package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageInput;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import com.example.springbootchatapplication1.model.repository.ChatMessageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {

    private ChatMessageRepository chatMessageRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository, ModelMapper modelMapper) {
        this.chatMessageRepository = chatMessageRepository;
        this.modelMapper = modelMapper;
    }

    public ChatMessageOutput getById(Long id) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(optionalEntity.get(), ChatMessageOutput.class);
    }

    public List<ChatMessageOutput> getAll() {
        List<ChatMessageEntity> userEntities = this.chatMessageRepository.findAll();
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, ChatMessageOutput.class)).
                collect(Collectors.toList());
    }

    @Transactional
    public ChatMessageOutput save(ChatMessageInput input) {
        ChatMessageEntity entity = this.modelMapper.map(input, ChatMessageEntity.class);

        return this.modelMapper.map(this.chatMessageRepository.saveAndGetEntity(entity), ChatMessageOutput.class);
    }

    @Transactional
    public ChatMessageOutput update(Long id, ChatMessageInput input) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.chatMessageRepository.update(optionalEntity.get()), ChatMessageOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        this.chatMessageRepository.delete(optionalEntity.get());
    }
}
