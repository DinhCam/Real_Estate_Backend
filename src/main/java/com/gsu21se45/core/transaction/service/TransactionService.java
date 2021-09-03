package com.gsu21se45.core.transaction.service;

import com.gsu21se45.core.real_estate.respo.RealEstateRepository;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GTransactionDto;
import com.gsu21se45.core.transaction.respo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface TransactionService {
    boolean createTransaction(CTransactionDto transactionDto);
    Page<GTransactionDto> getTransactionByUserId(String userId, Integer page, Integer size);

    @Service
    @Transactional
    class TransactionServiceImpl implements  TransactionService{
        @Autowired
        private TransactionRepository transactionRepository;

        @Autowired
        private RealEstateRepository realEstateRepository;

        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            try{
                if (transactionRepository.createTransaction(transactionDto)){
                    realEstateRepository.updateRealEstateStatusByCTransaction(transactionDto);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public Page<GTransactionDto> getTransactionByUserId(String userId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return transactionRepository.getTransactionByUserId(userId, pageable);
        }
    }
}