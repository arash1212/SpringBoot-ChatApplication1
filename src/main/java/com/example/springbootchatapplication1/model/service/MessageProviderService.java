package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.messageProvider.MessageProviderInput;
import com.example.springbootchatapplication1.model.dto.messageProvider.MessageProviderOutput;
import com.example.springbootchatapplication1.model.entity.relational.MessageProviderEntity;
import com.example.springbootchatapplication1.model.repository.relational.MessageProviderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageProviderService {

    private MessageProviderRepository messageProviderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MessageProviderService(MessageProviderRepository messageProviderRepository, ModelMapper modelMapper) {
        this.messageProviderRepository = messageProviderRepository;
        this.modelMapper = modelMapper;
    }

    public MessageProviderOutput getById(Long id) {
        Optional<MessageProviderEntity> optionalEntity = this.messageProviderRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), MessageProviderOutput.class);
    }

    public List<MessageProviderOutput> getAll(BaseFilter filter) {
        List<MessageProviderEntity> userEntities = this.messageProviderRepository.findAll(filter);
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, MessageProviderOutput.class)).collect(Collectors.toList());
    }

    public MessageProviderOutput getByTitle(String title) {
        Optional<MessageProviderEntity> optionalEntity = this.messageProviderRepository.find(Map.of("title", title));
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), MessageProviderOutput.class);
    }

    @Transactional
    public Long save(MessageProviderInput input) {
        MessageProviderEntity entity = this.modelMapper.map(input, MessageProviderEntity.class);

        Optional<MessageProviderEntity> optionalOldEntity = this.messageProviderRepository.getByTitle(input.getTitle());
        if (!optionalOldEntity.isEmpty()) {
            throw new CustomException(2);
        }
        return this.messageProviderRepository.save(entity);
    }

    @Transactional
    public MessageProviderOutput update(Long id, MessageProviderInput input) {
        Optional<MessageProviderEntity> optionalEntity = this.messageProviderRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.messageProviderRepository.update(optionalEntity.get()), MessageProviderOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<MessageProviderEntity> optionalEntity = this.messageProviderRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }
        this.messageProviderRepository.delete(optionalEntity.get());
    }

    /******************************************************************************************************************/

    public Optional<MessageProviderEntity> getEntityByTitle(String title) {
        return this.messageProviderRepository.getByTitle(title);
    }
}
