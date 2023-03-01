package com.c823.consorcio.controller;

import com.c823.consorcio.dto.MessageBasicDto;
import com.c823.consorcio.dto.MessageDto;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/messages")

public class MessageController {

  @Autowired
  private MessageService messageService;

  @Autowired
  IUserRepository iUserRepository;

  @PostMapping("send")
  public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto, Authentication authentication) {
    Long userId = getAuthenticatedUserId(authentication);
    if (userId == null) {
      // Si no se pudo obtener el ID del usuario autenticado, retorna un error 401 (Unauthorized)
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    messageDto.setUserId(userId);
    MessageDto message = this.messageService.sendMessage(messageDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("")
  public ResponseEntity<List<MessageBasicDto>> getMessagesauth(){
    List<MessageBasicDto> messagesauth = this.messageService.getMessagesauth();
    return ResponseEntity.ok().body(messagesauth);
  }

  @GetMapping("{userId}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<MessageDto>> getMessagesByUserId(@PathVariable Long userId){
    List<MessageDto> messages = this.messageService.getDetailsById(userId);
    return ResponseEntity.ok().body(messages);
  }

  @GetMapping("messageid/{messageId}")
  public ResponseEntity<MessageDto> getMessageById(@PathVariable Long messageId){
    MessageDto message = this.messageService.getdetails(messageId);
    return ResponseEntity.ok().body(message);
  }

  public Long getAuthenticatedUserId(Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        UserEntity user = iUserRepository.findByEmail(username);
        if (user != null) {
          return user.getUserId();
        }
      }
    }
    return null;
  }

}
