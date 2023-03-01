package com.c823.consorcio.mapper;

import com.c823.consorcio.dto.MessageBasicDto;
import com.c823.consorcio.dto.MessageDto;
import com.c823.consorcio.entity.MessageEntity;
import com.c823.consorcio.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMap {
  @Autowired
  private IUserRepository userRepository;

  public MessageEntity messageDto2Entity(MessageDto messageDto) {
    MessageEntity entity = new MessageEntity();
    entity.setDetail(messageDto.getDetail());
    entity.setSubject(messageDto.getSubject());
    entity.setMessage(messageDto.getMessage());
    entity.setUser(userRepository.findByUserId(messageDto.getUserId()));
    entity.setCreationDate(messageDto.getCreationDate());
    return entity;
  }

  public MessageDto messageEntity2Dto(MessageEntity entitySaved) {
    MessageDto dto = new MessageDto();
    dto.setId(entitySaved.getId());
    dto.setSubject(entitySaved.getSubject());
    dto.setMessage(entitySaved.getMessage());
    dto.setDetail(entitySaved.getDetail());
    dto.setUserId(entitySaved.getUser().getUserId());
    dto.setCreationDate(entitySaved.getCreationDate());
    return dto;
  }

  public List<MessageBasicDto> messageEntityList2BasicDtoList(List<MessageEntity> entities) {
    List<MessageBasicDto> messages = new ArrayList<>();
    for (MessageEntity entity : entities){
      messages.add(messagesEntity2BasicDto(entity));
    }
    return messages;
  }

  private MessageBasicDto messagesEntity2BasicDto(MessageEntity entity) {
    MessageBasicDto dto = new MessageBasicDto();
    dto.setCreationDate(entity.getCreationDate());
    dto.setMessage(entity.getMessage());
    dto.setUserId(entity.getUser().getUserId());
    dto.setUserName(entity.getUser().getFirstName() + " " + entity.getUser().getLastName());

    dto.setSubject(entity.getSubject());
    return dto;
  }

  public List<MessageDto> messageEntityList2DtoList(List<MessageEntity> entities) {
    List<MessageDto> messages = new ArrayList<>();
    for (MessageEntity entity : entities){
      messages.add(messageEntity2Dto(entity));
    }
    return messages;
  }
}
