package com.gsu21se45.core.transaction.service;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.respo.RealEstateRespo;
import com.gsu21se45.core.transaction.dto.CTransactionDto;
import com.gsu21se45.core.transaction.dto.GRealEstateAssignedStaffDto;
import com.gsu21se45.core.transaction.respo.TransactionRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface TransactionService {
    boolean createTransaction(CTransactionDto transactionDto);
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams r);

    @Service
    @Transactional
    class TransactionServiceImpl implements  TransactionService{
        @Autowired
        private RealEstateRespo realEstateSearch;
        @Autowired
        private TransactionRespo transactionRespo;

        @Override
        public boolean createTransaction(CTransactionDto transactionDto) {
            try{
                if (transactionRespo.createTransaction(transactionDto)){
                    realEstateSearch.updateRealEstate(transactionDto);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return transactionRespo.getRealEstateAssignStaff(r, pageable);
        }
    }
}