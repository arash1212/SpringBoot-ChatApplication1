package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.model.dto.user.UserInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginInput;
import com.example.springbootchatapplication1.model.dto.user.UserLoginOut;
import com.example.springbootchatapplication1.model.dto.user.UserOutput;
import com.example.springbootchatapplication1.model.entity.relational.UserAuthorityEntity;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import com.example.springbootchatapplication1.model.repository.UserRepository;
import com.example.springbootchatapplication1.utils.JWTService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserAuthorityService authorityService;
    private JWTService jwtService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserAuthorityService authorityService, JWTService jwtService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutput getById(Long id) {
        Optional<UserEntity> optionalEntity = this.userRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(optionalEntity.get(), UserOutput.class);
    }

    public List<UserOutput> getAll() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities.stream().filter(Objects::nonNull).map(x -> this.modelMapper.map(x, UserOutput.class)).
                collect(Collectors.toList());
    }

    public UserOutput getByUsername(String username) {
        Optional<UserEntity> optionalEntity = this.userRepository.find(Map.of("username", username));
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(optionalEntity.get(), UserOutput.class);
    }

    @Transactional
    public Long save(UserInput input) {
        UserEntity entity = this.modelMapper.map(input, UserEntity.class);

        entity.setEnabled(false);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setPassword(this.passwordEncoder.encode(input.getPassword()));

//        UserAuthorityEntity authority = this.authorityService.;

        return this.userRepository.save(entity);
    }

    @Transactional
    public UserOutput update(Long id, UserInput input) {
        Optional<UserEntity> optionalEntity = this.userRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        this.modelMapper.map(input, optionalEntity.get());
        return this.modelMapper.map(this.userRepository.update(optionalEntity.get()), UserOutput.class);
    }

    @Transactional
    public void delete(Long id) {
        Optional<UserEntity> optionalEntity = this.userRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        this.userRepository.delete(optionalEntity.get());
    }

    @Transactional
    public UserOutput assignAuthority(Long userId, List<Long> authorityIds) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<UserAuthorityEntity> authorityEntities = this.authorityService.getEntitiesById(authorityIds);
        optionalUserEntity.get().setAuthorities(new HashSet<>(authorityEntities));
        authorityEntities.forEach(x -> x.getUsers().add(optionalUserEntity.get()));

        return this.modelMapper.map(this.userRepository.update(optionalUserEntity.get()), UserOutput.class);
    }

    public UserLoginOut login(UserLoginInput input) {
        Optional<UserEntity> optionalEntity = this.userRepository.getByUsername(input.getUsername());
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        if (!this.passwordEncoder.matches(input.getPassword(), optionalEntity.get().getPassword())) {
            throw new EntityNotFoundException();
        }

        return new UserLoginOut(optionalEntity.get().getUsername(), this.jwtService.generateToken(input.getUsername()));
    }

    @Transactional
    public void enable(Long id) {
        Optional<UserEntity> entity = this.userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("enabled", true);

        this.userRepository.update(id, params);
    }

    @Transactional
    public void disable(Long id) {
        Optional<UserEntity> entity = this.userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("enabled", false);

        this.userRepository.update(id, params);
    }

}
