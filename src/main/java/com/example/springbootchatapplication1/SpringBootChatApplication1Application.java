package com.example.springbootchatapplication1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootChatApplication1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootChatApplication1Application.class, args);
    }

    //TODO
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
