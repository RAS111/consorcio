package com.c823.consorcio.controller;

import com.c823.consorcio.dto.BillPaymentDto;
import com.c823.consorcio.dto.TransactionBasicDto;
import com.c823.consorcio.dto.TransactionDto;
import com.c823.consorcio.service.ITransactionService;

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
@RequestMapping("/transactions")
@CrossOrigin("https://consorcio-production.up.railway.app")
public class TransactionController {

  @Autowired
  private ITransactionService transactionService;

  @PostMapping("/billPayment")
  public ResponseEntity<TransactionDto> billPayment(@RequestBody BillPaymentDto billPaymentDto){
   TransactionDto result = transactionService.sendInvoice(billPaymentDto);
   return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PostMapping("/sendPayment")
  public ResponseEntity<TransactionDto> sendPayment(@RequestBody BillPaymentDto billPaymentDto){
    TransactionDto result = transactionService.sendPayment(billPaymentDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("")
  public ResponseEntity<List<TransactionBasicDto>> getTransaction(){
    List<TransactionBasicDto> transactionList = transactionService.userBasicTransactions();
    return ResponseEntity.ok().body(transactionList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDto> getDetailsById(@PathVariable Long id){
    TransactionDto transaction = transactionService.getDetails(id);
    return ResponseEntity.ok().body(transaction);
  }






}
