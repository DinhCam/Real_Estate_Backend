package com.gsu21se45.controller.transaction;

import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transaction")
public class Transaction {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public HttpStatus createTransaction(@RequestBody CTransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
