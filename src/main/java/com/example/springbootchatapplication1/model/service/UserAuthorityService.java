package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityInput;
import com.example.springbootchatapplication1.model.dto.userAuthority.UserAuthorityOutput;
import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import com.example.springbootchatapplication1.model.repository.relational.UserAuthorityRepository;
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
public class UserAuthorityService {

    private UserAuthorityRepository authorityRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserAuthorityService(UserAuthorityRepository authorityRepository, ModelMapper modelMapper) {
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
    }

    public UserAuthorityOutput getById(Long id) {
        Optional<UserAuthorityEntity> optionalEntity = this.authorityRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), UserAuthorityOutput.class);
    }

    public List<UserAuthorityOutput> getAll() {
        List<UserAuthorityEntity> userEntities = this.authorityRepository.findAll();
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, UserAuthorityOutput.class)).
                collect(Collectors.toList());
    }

    public UserAuthorityOutput getByName(String authorityName) {
        Optional<UserAuthorityEntity> optionalEntity = this.authorityRepository.find(Map.of("authority", authorityName));
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        return this.modelMapper.map(optionalEntity.get(), UserAuthorityOutput.class);
    }

    @Transactional
    public Long save(UserAuthorityInput input) {
        UserAuthorityEntity entity = this.modelMapper.map(input, UserAuthorityEntity.class);
        entity.setAuthority(input.getAuthority().toLowerCase());

        Optional<UserAuthorityEntity> optionalOldEntity = this.authorityRepository.getByName(input.getAuthority());
        if (!optionalOldEntity.isEmpty()) {
            throw new CustomException(2);
        }
        return this.authorityRepository.save(entity);
    }

    @Transactional
    public UserAuthorityOutput update(Long id, UserAuthorityInput input) {
        Optional<UserAuthorityEntity> optionalEntity = this.authorityRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.authorityRepository.update(optionalEntity.get()), UserAuthorityOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<UserAuthorityEntity> optionalEntity = this.authorityRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(1);
        }
        this.authorityRepository.delete(optionalEntity.get());
    }


    /******************************************************************************************************************/
    public Optional<UserAuthorityEntity> getEntityById(Long id) {
        return this.authorityRepository.findById(id);
    }

    public Optional<UserAuthorityEntity> getEntityByName(String name) {
        return this.authorityRepository.getByName(name);
    }

    public List<UserAuthorityEntity> getEntitiesById(List<Long> ids) {
        return this.authorityRepository.findAll(ids);
    }

}
