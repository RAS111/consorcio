package com.c823.consorcio.mapper;

import com.c823.consorcio.dto.AccountBasicDto;
import com.c823.consorcio.dto.AccountDto;
import com.c823.consorcio.entity.AccountEntity;
import com.c823.consorcio.entity.ApartmentEntity;
import com.c823.consorcio.repository.ITransactionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMap {
  @Autowired
  private ITransactionRepository iTransactionRepository;
  @Autowired
  private TransactionMap transactionMap;
  @Autowired
  private ApartmentMap apartmentMap;

  public List<AccountDto> accountEntityList2DtoList(List<AccountEntity> entities) {
    List<AccountDto> accounts = new ArrayList<>();

    for (AccountEntity entity : entities){

      accounts.add(accountEntity2Dto(entity));
    }
    return accounts;
  }

  private AccountDto accountEntity2Dto(AccountEntity entity) {
    AccountDto account = new AccountDto();
    account.setAccountId(entity.getAccountId());
    account.setUpdateDate(entity.getUpdateDate());
    account.setBalance(entity.getBalance());
    account.setCreationDate(entity.getCreationDate());
    //account.setApartment(
      //  (ApartmentEntity) apartmentMap.apartmentEntityList2DtoList(entity.getUser().getApartments()));

   return account;
  }

  public List<AccountBasicDto> accountEntityList2BasicDto(List<AccountEntity> entities) {
    List<AccountBasicDto> accounts = new ArrayList<>();
    for(AccountEntity entity : entities){
      accounts.add(accountEntityBasic2Dto(entity));
    }
    return accounts;
  }

  private AccountBasicDto accountEntityBasic2Dto(AccountEntity entity) {
    AccountBasicDto dto = new AccountBasicDto();

    dto.setAccountId(entity.getAccountId());
    dto.setBalance(entity.getBalance());
    //dto.setTransaction(this.transactionMap.entityList2BasicDtoList(entity.getTransactions()));//TODO: COMPLETAR METODO EN MAPTRANSACTION

    return dto;
  }
}
