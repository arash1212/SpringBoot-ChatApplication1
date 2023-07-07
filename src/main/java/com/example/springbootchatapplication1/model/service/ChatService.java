package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.model.dto.chat.ChatInput;
import com.example.springbootchatapplication1.model.dto.chat.ChatOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import com.example.springbootchatapplication1.model.repository.ChatRepository;
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
public class ChatService {
    private ChatRepository chatRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ChatService(ChatRepository chatRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }

    public ChatOutput getById(Long id) {
        Optional<ChatEntity> optionalEntity = this.chatRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(optionalEntity.get(), ChatOutput.class);
    }

    public ChatOutput getByTitle(String title) {
        title = title.toUpperCase();
        Optional<ChatEntity> optionalEntity = this.chatRepository.getByTitle(title);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(optionalEntity.get(), ChatOutput.class);
    }

    public List<ChatOutput> getAll() {
        List<ChatEntity> userEntities = this.chatRepository.findAll();
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, ChatOutput.class)).
                collect(Collectors.toList());
    }

    @Transactional
    public Long save(ChatInput input) {
        ChatEntity entity = this.modelMapper.map(input, ChatEntity.class);
        entity.setTitle(input.getTitle().toUpperCase());

        return this.chatRepository.save(entity);
    }

    @Transactional
    public ChatOutput update(Long id, ChatInput input) {
        Optional<ChatEntity> optionalEntity = this.chatRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.chatRepository.update(optionalEntity.get()), ChatOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<ChatEntity> optionalEntity = this.chatRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        this.chatRepository.delete(optionalEntity.get());
    }
}
