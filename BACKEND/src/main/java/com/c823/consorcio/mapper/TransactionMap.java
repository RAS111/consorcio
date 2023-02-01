package com.c823.consorcio.mapper;

import com.c823.consorcio.dto.AccountBasicDto;
import com.c823.consorcio.dto.TransactionBasicDto;
import com.c823.consorcio.dto.TransactionDto;
import com.c823.consorcio.entity.AccountEntity;
import com.c823.consorcio.entity.TransactionEntity;
import com.c823.consorcio.entity.UserEntity;
import com.c823.consorcio.repository.ITransactionRepository;
import com.c823.consorcio.repository.IUserRepository;
import com.c823.consorcio.repository.IaccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TransactionMap {

  @Autowired
  private IaccountRepository iaccountRepository;
  @Autowired
  private ITransactionRepository iTransactionRepository;
  @Autowired
  private IUserRepository iUserRepository;

  public TransactionEntity transactionDto2Entity(TransactionDto transactionDto) {
    TransactionEntity entity = new TransactionEntity();
    entity.setTransactionId(transactionDto.getTransactionId());
    entity.setType(transactionDto.getType());
    entity.setAmount(transactionDto.getAmount());
    entity.setTransactionDate(transactionDto.getTransactionDate());
    entity.setAccountId(this.iaccountRepository.findByAccountId(transactionDto.getAccountId()));

    return entity;

  }


  public List<TransactionBasicDto> transactionEntityList2BasicDtoList(List<TransactionEntity> entities) {
    List<TransactionBasicDto> transactions = new ArrayList<>();
    for(TransactionEntity entity : entities){
      transactions.add(transactionEntity2BasicDto(entity));
    }
    return transactions;

  }

  private TransactionBasicDto transactionEntity2BasicDto(TransactionEntity entity) {
    TransactionBasicDto dto = new TransactionBasicDto();
    dto.setTransactionId(entity.getTransactionId());
    dto.setType(entity.getType());
    dto.setAmount(entity.getAmount());

    return dto;
  }

  public TransactionDto transactionEntity2Dto(TransactionEntity transactionDetail) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserEntity user = this.iUserRepository.findByEmail(email);
    TransactionDto dto = new TransactionDto();
    dto.setTransactionId(transactionDetail.getTransactionId());
    dto.setAmount(transactionDetail.getAmount());
    dto.setType(transactionDetail.getType());
    dto.setTransactionDate(transactionDetail.getTransactionDate());
    dto.setDescription(transactionDetail.getDescription());
    dto.setAccountId(transactionDetail.getAccountId().getAccountId());
    //dto.setDestinationAccountId(iTransactionRepository.findByAccountId(user));//TODO: VISUALIZAR CUENTA DESTINO

    return dto;
  }
}
