package com.example.springbootchatapplication1.model.service;

import com.example.springbootchatapplication1.exception.CustomException;
import com.example.springbootchatapplication1.model.dto.base.BaseFilter;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageInput;
import com.example.springbootchatapplication1.model.dto.chatMessage.ChatMessageOutput;
import com.example.springbootchatapplication1.model.entity.relational.ChatEntity;
import com.example.springbootchatapplication1.model.entity.relational.ChatMessageEntity;
import com.example.springbootchatapplication1.model.entity.relational.UserEntity;
import com.example.springbootchatapplication1.model.repository.relational.ChatMessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatMessageServiceTest {

    private final int LENGTH = 5;
    private final Long ID = 1L;
    private final Long CHAT_ID = 1L;
    private final Long CREATOR_ID = 1L;
    private final Long MODIFIER_ID = 1L;
    private final Long RECEIVER_ID = 1L;
    private final String TEXT = "test";
    private UserEntity creator;
    private BaseFilter baseFilter;
    private ChatMessageInput chatMessageInput;
    private ChatMessageEntity chatMessageEntity;
    private ChatMessageOutput chatMessageOutput;
    private final List<ChatMessageEntity> chatMessageEntityList = new ArrayList<>(LENGTH);
    private final List<ChatMessageOutput> chatMessageOutputList = new ArrayList<>(LENGTH);

    @Mock
    private ChatMessageRepository chatMessageRepository;
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;
    @Spy
    @InjectMocks
    private ChatMessageService chatMessageService;

    @BeforeEach
    void setUp() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime lastUpdateAt = LocalDateTime.now().plusDays(1);
        //chatMessageInput Init
        this.chatMessageInput = new ChatMessageInput();
        this.chatMessageInput.setChatId(CHAT_ID);
        this.chatMessageInput.setText(TEXT);
        this.chatMessageInput.setReceiverId(RECEIVER_ID);

        this.baseFilter = new BaseFilter();
        //chat Init
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(CHAT_ID);
        //receiver Init
        UserEntity receiver = new UserEntity();
        receiver.setId(RECEIVER_ID);
        //creator Init
        creator = new UserEntity();
        creator.setId(CREATOR_ID);
        //modifier Init
        UserEntity lastModifier = new UserEntity();
        lastModifier.setId(MODIFIER_ID);

        //chatMessageEntityList Fill
        for (int i = 0; i < LENGTH; i++) {
            //chatMessageEntity Init
            ChatMessageEntity entity = new ChatMessageEntity();
            entity.setId(ID + i);
            entity.setChatId(CHAT_ID);
            entity.setChat(chatEntity);
            entity.setCreator(creator);
            entity.setText(TEXT);
            entity.setReceiverId(RECEIVER_ID);
            entity.setCreatedAt(createdAt);
            entity.setCreatorId(CREATOR_ID);
            entity.setReceiver(receiver);
            entity.setLastModifier(lastModifier);
            entity.setLastModifierId(MODIFIER_ID);
            entity.setLastUpdateAt(lastUpdateAt);

            chatMessageEntityList.add(entity);
            chatMessageOutputList.add(new ChatMessageOutput(entity));
        }
        this.chatMessageEntity = chatMessageEntityList.get(0);
        this.chatMessageOutput = chatMessageOutputList.get(0);
    }

    @Test
    void getById_Given_ChatMessageId_Then_Return_ChatMessageOutput() {
        doReturn(Optional.of(chatMessageEntity)).when(chatMessageRepository).findById(ID);
        ChatMessageOutput actual = this.chatMessageService.getById(ID);
        ChatMessageOutput expected = this.chatMessageOutput;
        //Assertions
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getCreator().getId(), actual.getCreator().getId());
        assertEquals(expected.getReceiver().getId(), actual.getReceiver().getId());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getLastUpdateAt(), actual.getLastUpdateAt());
        //Verifies
        verify(chatMessageService, times(1)).getById(ID);
        verify(chatMessageRepository, times(1)).findById(ID);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void getById_Should_ThrowException_When_Id_Does_Not_Exist() {
        doReturn(Optional.empty()).when(chatMessageRepository).findById(ID);
        //Assertions
        Assertions.assertThrows(CustomException.class, () -> {
            this.chatMessageService.getById(ID);
        });
        //Verifies
        verify(chatMessageService, times(1)).getById(ID);
        verify(chatMessageRepository, times(1)).findById(ID);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void getAll_Given_Filter_Then_Return_AllChatMessages_Matching_Filter() {
        doReturn(chatMessageEntityList).when(chatMessageRepository).findAll(baseFilter);
        List<ChatMessageOutput> actual = this.chatMessageService.getAll(baseFilter);
        List<ChatMessageOutput> expected = chatMessageOutputList;
        //Assertions
        for (int i = 0; i < LENGTH; i++) {
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getChatId(), actual.get(i).getChatId());
            assertEquals(expected.get(i).getText(), actual.get(i).getText());
            assertEquals(expected.get(i).getCreator().getId(), actual.get(i).getCreator().getId());
            assertEquals(expected.get(i).getReceiver().getId(), actual.get(i).getReceiver().getId());
            assertEquals(expected.get(i).getCreatedAt(), actual.get(i).getCreatedAt());
            assertEquals(expected.get(i).getLastUpdateAt(), actual.get(i).getLastUpdateAt());
        }
        //Verifies
        verify(chatMessageService, times(1)).getAll(baseFilter);
        verify(chatMessageRepository, times(1)).findAll(baseFilter);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void save_Given_ChatMessageInput_Then_Save_Entity_And_Return_ChatMessageOutput() {
        doReturn(chatMessageEntity).when(chatMessageRepository).saveAndGetEntity(chatMessageEntity);
        doReturn(chatMessageEntity).when(modelMapper).map(chatMessageInput, ChatMessageEntity.class);
        doReturn(creator).when(userService).getEntity(chatMessageEntity.getCreatorId());
        this.chatMessageService.save(chatMessageInput);
        verify(chatMessageService, times(1)).save(chatMessageInput);
        verify(chatMessageRepository, times(1)).saveAndGetEntity(chatMessageEntity);
        verify(modelMapper, times(1)).map(chatMessageInput, ChatMessageEntity.class);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void update_Given_ChatMessageId_And_Input_Then_Return_Updated_Output() {
        doReturn(Optional.of(chatMessageEntity)).when(chatMessageRepository).findById(ID);
        doReturn(chatMessageEntity).when(chatMessageRepository).update(chatMessageEntity);
        ChatMessageOutput actual = this.chatMessageService.update(ID, chatMessageInput);
        ChatMessageOutput expected = chatMessageOutput;
        //Assertions
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getCreator().getId(), actual.getCreator().getId());
        assertEquals(expected.getReceiver().getId(), actual.getReceiver().getId());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getLastUpdateAt(), actual.getLastUpdateAt());
        //Verifies
        verify(chatMessageService, times(1)).update(ID, chatMessageInput);
        verify(chatMessageRepository, times(1)).findById(ID);
        verify(modelMapper, times(1)).map(chatMessageInput, chatMessageEntity);
        verify(chatMessageRepository, times(1)).update(chatMessageEntity);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void update_Should_ThrowException_When_Id_Does_Not_Exist() {
        doReturn(Optional.empty()).when(chatMessageRepository).findById(ID);
        //Assertions
        Assertions.assertThrows(CustomException.class, () -> {
            this.chatMessageService.update(ID, chatMessageInput);
        });
        //Verifies
        verify(chatMessageService, times(1)).update(ID, chatMessageInput);
        verify(chatMessageRepository, times(1)).findById(ID);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void delete_Given_ChatMessageId_And_Delete_Entity() {
        doReturn(Optional.of(chatMessageEntity)).when(chatMessageRepository).findById(ID);
        this.chatMessageService.delete(ID);
        verify(chatMessageService, times(1)).delete(ID);
        verify(chatMessageRepository, times(1)).delete(chatMessageEntity);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }

    @Test
    void delete_Should_ThrowException_When_Id_Does_Not_Exist() {
        doReturn(Optional.empty()).when(chatMessageRepository).findById(ID);
        //Assertions
        Assertions.assertThrows(CustomException.class, () -> {
            this.chatMessageService.delete(ID);
        });
        //Verifies
        verify(chatMessageService, times(1)).delete(ID);
        verify(chatMessageRepository, times(1)).findById(ID);
        verifyNoMoreInteractions(chatMessageRepository, userService, modelMapper, chatMessageService);
    }
}