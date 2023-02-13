package com.c823.consorcio.service;

import com.c823.consorcio.dto.MessageBasicDto;
import com.c823.consorcio.dto.MessageDto;
import java.util.List;

public interface MessageService {

  MessageDto sendMessage(MessageDto messageDto);

  List<MessageBasicDto> getMessagesauth();

  List<MessageDto> getDetailsById(Long userId);

  MessageDto getdetails(Long message_id);
}
