package com.example.springbootchatapplication1.model.entity.relational.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EnumMessageType {
    EMAIL(1L, "ایمیل"),
    SMS(2L, "پیامک");

    private Long id;
    private String title;

    public static EnumMessageType of(Long typeId) {
        return Arrays.stream(values()).filter(x -> Objects.equals(x.id, typeId)).findFirst().get();
    }
}
