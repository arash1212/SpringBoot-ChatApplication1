package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.messageType.EnumMessageTypeOutput;
import com.example.springbootchatapplication1.model.entity.relational.enums.entity.EnumMessageTypeEntity;
import com.example.springbootchatapplication1.model.repository.relational.EnumMessageTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnumMessageTypeService {
    private EnumMessageTypeRepository enumMessageTypeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EnumMessageTypeService(EnumMessageTypeRepository enumMessageTypeRepository, ModelMapper modelMapper) {
        this.enumMessageTypeRepository = enumMessageTypeRepository;
        this.modelMapper = modelMapper;
    }

    public EnumMessageTypeOutput getById(Long id) {
        Optional<EnumMessageTypeEntity> optionalEntity = this.enumMessageTypeRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), EnumMessageTypeOutput.class);
    }

    public List<EnumMessageTypeOutput> getAll(BaseFilter filter) {
        List<EnumMessageTypeEntity> messageTypeEntities = this.enumMessageTypeRepository.findAll(filter);
        return messageTypeEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, EnumMessageTypeOutput.class)).
                collect(Collectors.toList());
    }

}
