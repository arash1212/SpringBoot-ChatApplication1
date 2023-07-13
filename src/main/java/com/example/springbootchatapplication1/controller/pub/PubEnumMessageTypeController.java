package com.example.springbootchatapplication1.controller.pub;


import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.messageType.EnumMessageTypeOutput;
import com.example.springbootchatapplication1.model.service.EnumMessageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pub-Enum-MessageType")
@Validated
@RestController
@RequestMapping(path = "/pub/enum/message-type")
public class PubEnumMessageTypeController {
    @Autowired
    private EnumMessageTypeService messageTypeService;

    @Operation(summary = "Get messageType by id", description = "Get messageType by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnumMessageTypeOutput> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.messageTypeService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get All messageTypes", description = "Get All messageTypes")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnumMessageTypeOutput>> getAll(@Valid @ModelAttribute BaseFilter filter) {
        return new ResponseEntity<>(this.messageTypeService.getAll(filter), HttpStatus.OK);
    }
}
