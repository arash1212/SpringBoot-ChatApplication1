package com.example.springbootchatapplication1.model.dto.chat;

import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatFilter extends BaseFilter {
    private Long id;
    private String title;
    //    private LocalDateTime createdAt;
//    private LocalDateTime lastUpdateAt;
    private Long creatorId;
    private Long lastModifierId;
}
