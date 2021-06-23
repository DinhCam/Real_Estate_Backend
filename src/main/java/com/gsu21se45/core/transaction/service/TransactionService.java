package com.gsu21se45.core.transaction.service;

import com.gsu21se45.core.real_estate.respo.RealEstateRespo;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.respo.TransactionRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface TransactionService {
    boolean createTransaction(CTransactionDto transactionDto);

    @Service
    @Transactional
    class TransactionServiceImpl implements  TransactionService{
        @Autowired
        private TransactionRespo transactionRespo;

        @Autowired
        private RealEstateRespo realEstateRespo;

        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            try{
                if (transactionRespo.createTransaction(transactionDto)){
                    realEstateRespo.updateRealEstateByCTransaction(transactionDto);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}