package com.c823.consorcio.controller;

import com.c823.consorcio.dto.MessageBasicDto;
import com.c823.consorcio.dto.MessageDto;
import com.c823.consorcio.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@CrossOrigin("https://live-to.sytes.net/")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @PostMapping("send")
  public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto){
    MessageDto message = this.messageService.sendMessage(messageDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("")
  public ResponseEntity<List<MessageBasicDto>> getMessagesauth(){
    List<MessageBasicDto> messagesauth = this.messageService.getMessagesauth();
    return ResponseEntity.ok().body(messagesauth);
  }

  @GetMapping("{userId}")//TODO: solo administrador
  public ResponseEntity<List<MessageDto>> getMessagesByUserId(@PathVariable Long userId){
   List<MessageDto> messages = this.messageService.getDetailsById(userId);
    return ResponseEntity.ok().body(messages);
  }

  @GetMapping("messageid/{messageId}")
  public ResponseEntity<MessageDto> getMessageById(@PathVariable Long messageId){
    MessageDto message = this.messageService.getdetails(messageId);
    return ResponseEntity.ok().body(message);
  }

}
