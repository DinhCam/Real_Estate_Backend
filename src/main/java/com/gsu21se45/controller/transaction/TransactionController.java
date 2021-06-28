package com.gsu21se45.controller.transaction;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.core.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/createTransaction")
    public HttpStatus createTransaction(@RequestBody CTransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "/getTransactionByUserId/{userId}/{page}")
    public PaginationResponse<GTransactionDto> getTransactionByUserId(@PathVariable String userId, @PathVariable Integer page){
        Integer size = 30;
        Page<GTransactionDto> data = transactionService.getTransactionByUserId(userId, page, size);
        return new PaginationResponse<>(data);
    }
}