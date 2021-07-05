package com.gsu21se45.controller.transaction;

import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.core.transaction.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/createTransaction")
    @ApiOperation("Create a new transaction")
    public HttpStatus createTransaction(@RequestBody CTransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "/getTransactionByUserId/{userId}/{page}")
    @ApiOperation("Get all transaction of a user")
    public PaginationResponse<GTransactionDto> getTransactionByUserId(@PathVariable String userId, @PathVariable Integer page){
        Integer size = 30;
        Page<GTransactionDto> data = transactionService.getTransactionByUserId(userId, page, size);
        return new PaginationResponse<>(data);
    }
}