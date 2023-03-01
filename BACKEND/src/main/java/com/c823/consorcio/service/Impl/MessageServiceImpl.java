package com.c823.consorcio.service.Impl;

import com.c823.consorcio.auth.dto.ResponseUserDto;
import com.c823.consorcio.auth.dto.UserAuthDto;
import com.c823.consorcio.auth.exception.ParamNotFound;
import com.c823.consorcio.dto.MessageBasicDto;
import com.c823.consorcio.dto.MessageDto;
import com.c823.consorcio.entity.MessageEntity;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.mapper.MessageMap;
import com.c823.consorcio.repository.IMessageRepository;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.service.IUserService;
import com.c823.consorcio.service.MessageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageMap messageMap;
  @Autowired
  private IMessageRepository messageRepository;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IUserService userService;

  @Override
  public MessageDto sendMessage(MessageDto messageDto) {
    MessageEntity messageEntity = messageMap.messageDto2Entity(messageDto);
    MessageEntity entitySaved = messageRepository.save(messageEntity);
    MessageDto result = messageMap.messageEntity2Dto(entitySaved);
    return result;
  }

  @Override
  public List<MessageBasicDto> getMessagesauth() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = userRepository.findByEmail(email);
    Long userId = user.getUserId();
    List<MessageEntity> messageEntityList = messageRepository.findAllByUser(user);
    List<MessageBasicDto> messagesList = messageMap.messageEntityList2BasicDtoList(messageEntityList);

    return messagesList;
  }

  @Override
  public List<MessageDto> getDetailsById(Long userId) {
    UserEntity user = userRepository.findById(userId).orElseThrow(
            ()-> new ParamNotFound("Invalid ID"));
    List<MessageEntity> messageEntityList = messageRepository.findAllByUser(user);
    List<MessageDto> messageList = messageMap.messageEntityList2DtoList(messageEntityList);

    return messageList;
  }

  @Override
  public MessageDto getdetails(Long message_id) {
    MessageEntity messageEntity = messageRepository.findById(message_id).orElseThrow(
            ()->new ParamNotFound("Invalid ID"));
    MessageDto messageDto = messageMap.messageEntity2Dto(messageEntity);
    return messageDto;
  }
}
