package com.example.springbootchatapplication1.model.dto.messageType;

import com.example.springbootchatapplication1.model.entity.relational.enums.entity.EnumMessageTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumMessageTypeOutput {
    private Long id;
    private String title;

    public EnumMessageTypeOutput(EnumMessageTypeEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
