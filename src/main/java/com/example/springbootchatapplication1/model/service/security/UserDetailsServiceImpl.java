package com.example.springbootchatapplication1.model.service.security;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import com.example.springbootchatapplication1.model.repository.relational.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalEntity = this.userRepository.getByUsername(username);
        if (optionalEntity.isEmpty()) {
            throw new CustomException(4);
        }
        return optionalEntity.get();
    }
}
