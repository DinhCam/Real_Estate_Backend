package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.repository.TransactionRepository;
import com.gsu21se45.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Conversation> findBuyerIdByRealEstate(int realEstateId) {
        return transactionRepository.findBuyerIdByRealEstate(realEstateId);
    }
}
