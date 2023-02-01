package com.c823.consorcio.service;

import com.c823.consorcio.dto.BillPaymentDto;
import com.c823.consorcio.dto.TransactionBasicDto;
import com.c823.consorcio.dto.TransactionDto;
import java.util.List;

public interface ITransactionService {


  TransactionDto sendInvoice(BillPaymentDto billPaymentDto);

  TransactionDto createTransaction(TransactionDto transactionDto);

  TransactionDto sendPayment(BillPaymentDto billPaymentDto);

  List<TransactionBasicDto> userBasicTransactions();

  TransactionDto getDetails(Long id);
}
