package com.gsu21se45.service;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.RealEstate;

import java.util.List;

public interface TransactionService {
    List<Conversation> findBuyerIdByRealEstate(int realEstateId);
}
