package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageInput;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import com.example.springbootchatapplication1.model.repository.relational.ChatMessageRepository;
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
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository, UserService userService, ModelMapper modelMapper) {
        this.chatMessageRepository = chatMessageRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public ChatMessageOutput getById(Long id) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), ChatMessageOutput.class);
    }

    public List<ChatMessageOutput> getAll(BaseFilter filter) {
        List<ChatMessageEntity> userEntities = this.chatMessageRepository.findAll(filter);
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, ChatMessageOutput.class)).
                collect(Collectors.toList());
    }

    @Transactional
    public ChatMessageOutput save(ChatMessageInput input) {
        ChatMessageEntity entity = this.modelMapper.map(input, ChatMessageEntity.class);

        ChatMessageEntity newEntity = this.chatMessageRepository.saveAndGetEntity(entity);
        UserEntity creator = this.userService.getEntity(newEntity.getCreatorId());
        newEntity.setCreator(creator);
        return this.modelMapper.map(newEntity, ChatMessageOutput.class);
    }

    @Transactional
    public ChatMessageOutput update(Long id, ChatMessageInput input) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.chatMessageRepository.update(optionalEntity.get()), ChatMessageOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<ChatMessageEntity> optionalEntity = this.chatMessageRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }
        this.chatMessageRepository.delete(optionalEntity.get());
    }

    /******************************************************************************************************************/


}
