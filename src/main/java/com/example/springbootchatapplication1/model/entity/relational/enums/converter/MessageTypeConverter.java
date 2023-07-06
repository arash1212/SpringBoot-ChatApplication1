package com.example.springbootchatapplication1.model.entity.relational.enums.converter;

import com.example.springbootchatapplication1.model.entity.relational.enums.EnumMessageType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MessageTypeConverter implements AttributeConverter<EnumMessageType, Long> {


    @Override
    public Long convertToDatabaseColumn(EnumMessageType enumMessageType) {
        return enumMessageType.getId();
    }

    @Override
    public EnumMessageType convertToEntityAttribute(Long aLong) {
        return EnumMessageType.of(aLong);
    }
}
